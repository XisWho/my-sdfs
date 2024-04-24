package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 客户端的一个NIOClient，负责跟数据节点进行网络通信
 */
public class NIOClient {

	public static final Integer SEND_FILE = 1;
	public static final Integer READ_FILE = 2;

	/**
	 * 发送一个文件过去
	 * @param hostname
	 * @param nioPort
	 * @param file
	 * @param fileSize
	 */
	public static void sendFile(String hostname, int nioPort,
								byte[] file, String filename, long fileSize) {
		// 建立一个短连接，发送完一个文件就释放网络连接
		Selector selector = null;
		SocketChannel channel = null;
		ByteBuffer buffer = null;

		try {
			selector = Selector.open();

			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(hostname, nioPort)); // 正是因为底层是走的非阻塞
			// 所以在这里就不会阻塞住
			// 他仅仅只是发起了一个连接的请求，但是直接就返回了，你可以干别的事情了
			// 非阻塞，就是需要你在底层不停的轮询探查他是否执行结束了
			// 异步，你发起一个请求就不管了，人家执行完了以后会反过来通知你
			channel.register(selector, SelectionKey.OP_CONNECT);
			// 这边的这个意思，就是告诉Selector说，我把这个SocketChannel给你
			// 现在需要你在轮询的过程中，关注的是这个SocketChannel是否有OP_CONNECT事件
			// Selector就不会不断的去探查SocketChannel是否可以建立连接

			boolean sending = true;

			while(sending){
				selector.select();   // 同步的模式，NIO同步非阻塞
				// Selector在底层监听多个SocketChannel的时候是采取的是非阻塞的方式
				// 同步的方式，对于调用Selector的线程而言，必须在这里
				// 需要进行同步等待的模式，等待说，必须得得等到有某个SocketChannel有事件处理

				// BIO，同步阻塞，就是你一个线程必须阻塞在一个Socket上去等待里面的请求接收
				// 或者是等待发送响应出去
				// 而且因为阻塞了，必然是同步的，同步等待，阻塞在一个Socket上看是否有请求或者响应
				// 所以必须是每个连接都有一个独立的线程去维护

				// AIO，NIO2，异步非阻塞，意思就是说我发起一个请求，去看某个连接是否有请求或者响应
				// 接着就不管了，提供一个回调函数给AIO接口可以了
				// 人家后续在一个连接上收到了请求或者响应之后，就回调我的回调函数就可以了

				Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
				// 在NIO技术体系里，有一个核心的概念，叫做SelectionKey
				// 你大概可以认为每个SocketChannel就对应了一个SelectionKey，就对应了一个Socket连接
				// 一个Socket连接就对应了底层的TCP连接，封装数据包和传输过去的

				// 我推测人家在设计JDK的API的时候，SelectionKey
				// Selector在底层如果说发现了某个连接有事件可以处理，此时这个连接就是被选中的一个连接
				// Selection被选中的一个意思，Key？大概来说就是对应着一个连接的标识

				// SelectioniKey，对应的其实就是意思，就是说一个被选中的连接的标识
				// 老外在对API进行设计和命名的时候，很多API都设计的非常的棒，但是也有的地方设计的不太好
				// NIO，SelectionKey就是一个很不好的API，SocketChannel的关系是什么呢？

				// 如果这个事儿让我来设计，用最自然的大脑思考的过程来判断的话
				// 应该就是说
				// Iterator<SocketChannel> selectedChannels = selector.selectedChannels().iterator();
				// SelectionKey对应的也是SocketChannel
				// 本身SocketChannel他就是封装了底层的Socket，把SelectionKey的功能给他加入进去不就可以了吗

				while(keysIterator.hasNext()){
					SelectionKey key = (SelectionKey) keysIterator.next();
					keysIterator.remove();

					// 大概来思考一下，在这里，Set<SelectionKey实际上是Selector内部的一个集合
					// 针对这个Set获取了一个Iterator进行迭代遍历
					// 如果说不调用Iterator.remove，会发生什么事情呢？

					// 假设说，此时有一个SocketChannel有一个OP_CONNECT事件
					// 他对应的SelectionKey此时就会在Set<SelectionKey>集合里，此时我们可以在这里进行一个处理
					// 如果你没有调用Iterator.remove，此时会导致这个SelectionKey继续留存在Set集合里

					// NIOServer允许进行连接的话
					// 他之所以抽取一个所谓的SelectionKey API出来，就是封装了一些功能
					// 可以通过SelectionKey判断一下，当前底层的连接上发生了什么事件
					if(key.isConnectable()){
						// 调用到isConnectiontable的时候有三种情况
						// 第一种情况：就是连接已经建立成功了，此时在下面不会有任何的阻塞直接返回true
						// 第二种情况：连接建立失败了，此时在下面会抛出IOException异常
						// 第三种情况：连接还没彻底成功，在进行中

						channel = (SocketChannel) key.channel(); // SelectionKey API
						// 如果是我来设计JDK的这套NIO API，SelectionKey这个概念，让人很迷惑的概念
						// 坚决不会引入的

						// 判断一下是否这个channel上有TCP连接过程正在执行
						if(channel.isConnectionPending()){
							// 把三次握手做完，TCP连接建立好了
							while(!channel.finishConnect()) {
								Thread.sleep(100);
							}
						}
						System.out.println("完成与服务端的连接的建立......");

						// 封装文件的请求数据
						buffer = ByteBuffer.allocate(
								4 + 4 + filename.getBytes().length + 8 + (int)fileSize);
						System.out.println("准备发送的数据包大小为：" + buffer.capacity());
						buffer.putInt(SEND_FILE); // 先放入4个字节的int，是一个数字，代表了本次是个什么请求？
						buffer.putInt(filename.getBytes().length); // 先放入4个字节的int，是一个数字，527，,336，代表了这里的文件名有多少个字节
						buffer.put(filename.getBytes()); // 再把真正的文件名给放入进去
						buffer.putLong(fileSize); // long对应了8个字节，放到buffer里去
						buffer.put(file);
						buffer.rewind(); // 一直在不停的写，写进去的数据刚好和ByteBuffer的总容量是一样大的
						// 此时limit本身就应该和ByteBuffer的总容量是一致的了
						// 直接调用rewind把position设置为0就可以了

						// 假设图片是128kb，4byte + 4byte + 26byte + 8byte + 128kb，数据包的大小就是这样

						// requestId | filenameLength | filename | fileSize | file

						// 如果大家去跟着我大数据的课，kafka源码剖析的课去看的话，就会知道
						// kafka里面是用了一模一样的方式，基于一个预定义好的二进制规范，按照规范往ByteBuffer里
						// 写入几个字节的size，几个字节的数据

						int sent = channel.write(buffer);  // 尝试从position = 0开始写，一直写到limit那儿为止
						System.out.println("已经发送了" + sent+ " bytes的数据到服务端去");
						// 如果一旦写完了buffer中的数据，那么reamining就会是0，就没有剩余的数据可以写了
						// 调用这个write方法的时候，他会尝试将buffer中的remaining的数据发送出去
						// remaining = limit - position，也就是说当前你剩余可以读取的数据的字节的数量

						// p = 0，n = 100
						// p + n - 1 = 99，最后一个字节的位置，write方法返回的就是p + n = 100个字节
						// 他从buffer的position开始写出去了多少个字节
						// 这个写出去的字节数量，n一定是小于等于r的，r = remaining = limit - position

						// 比如说此时buffer的position = 0，limit = 102，此时可以写的字节数量一共是102个字节，r
						// 但是此时一次write就从position = 0写到了position = 99，就写了100个字节，n
						// n <= r

						// 在这个地方，通常来说都会尽量把buffer里所有的r都写出去的
						// 但是有可能会出现仅仅写了一部分字节出去，有一部分的字节没写出去

						// 此时就会出现了一个客户端传输大数据包时的一个类拆包的问题
						// 这个问题之前我们在编程开发的时候，都遇到过类似的这种类拆包的问题

						// 如果到这一步还有remaining的话，就说明position到limit之间的数据并没有写完
						if(buffer.hasRemaining()) {
							System.out.println("本次数据包没有发送完毕，下次会继续发送.......");
							key.interestOps(SelectionKey.OP_WRITE);
						} else {
							System.out.println("本次数据包发送完毕，准备读取服务端的响应......");
							key.interestOps(SelectionKey.OP_READ);
						}
					}
					// 对于大数据包的拆包，再次尝试发送数据出去
					else if(key.isWritable()) {
						channel = (SocketChannel) key.channel();
						int sent = channel.write(buffer);
						System.out.println("上一次数据包没有发送完毕，本次继续发送了" + sent + " bytes");
						if(!buffer.hasRemaining()) {
							System.out.println("本次数据包没有发送完毕，下次会继续发送.......");
							key.interestOps(SelectionKey.OP_READ);
						}
					}
					// 接收到NIOServer的响应
					else if(key.isReadable()){
						channel = (SocketChannel) key.channel();

						buffer = ByteBuffer.allocate(1024);
						int len = channel.read(buffer);
						buffer.flip();

						if(len > 0) {
							System.out.println("[" + Thread.currentThread().getName()
									+ "]收到" + hostname + "的响应：" + new String(buffer.array(), 0, len));
							sending = false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(channel != null){
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(selector != null){
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取文件
	 * @param hostname 数据节点的hostname
	 * @param nioPort 数据节点的nio端口号
	 * @param filename 文件名
	 */
	public static byte[] readFile(String hostname, int nioPort, String filename) {
		ByteBuffer fileLengthBuffer = null;
		Long fileLength = null;
		ByteBuffer fileBuffer = null;

		byte[] file = null;

		SocketChannel channel = null;
		Selector selector = null;

		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(hostname, nioPort));

			selector = Selector.open();
			channel.register(selector, SelectionKey.OP_CONNECT);

			boolean reading = true;

			while(reading){
				selector.select();

				Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
				while(keysIterator.hasNext()){
					SelectionKey key = (SelectionKey) keysIterator.next();
					keysIterator.remove();

					// NIOServer允许进行连接的话
					if(key.isConnectable()){
						channel = (SocketChannel) key.channel();

						// 判断一下是否这个channel上有TCP连接过程正在执行
						if(channel.isConnectionPending()){
							// 把三次握手做完，TCP连接建立好了
							while(!channel.finishConnect()) {
								Thread.sleep(100);
							}
						}
						System.out.println("完成与服务端的连接的建立......");

						// 在这里，第一步，一旦建立起来一个连接，直接就是发送一个请求过去
						// 意思就是说，你想要读取一个文件
						// 其实你就应该先发送你这个请求要做的事情，比如用Integer类型来进行代表，4个字节，int数字
						// 1：发送文件过去；2：读取文件过来
						// 2+文件名的字节数+实际的文件名
						// 客户端发送完请求之后，立马就是关注OP_READ事件，他要等着去读取人家发送过来的数据
						// 一旦说读取完毕了文件，再次关注OP_WRITE，发送一个SUCCESS过去给人家

						// 服务端而言，先读取开头的4个字节，判断一下 你要干什么
						// 如果是1，发送文件，就转入之前写的那套代码；如果是2，读取文件，那么就新写一套逻辑
						// 人家就需要解析出来你的文件名，转换为他的本地存储的路径，把文件读取出来，给你发送过来
						// 一旦发送完毕了文件之后，就关注OP_READ事件，等待读取人家发送过来的结果，SUCCESS

						// 最后都完毕之后，双方都要去断开各自的连接

						byte[] filenameBytes = filename.getBytes();

						// int（4个字节）int（4个字节）文件名（N个字节）

						ByteBuffer readFileRequest = ByteBuffer.allocate(4 + 4 + filenameBytes.length);
						readFileRequest.putInt(READ_FILE);
						readFileRequest.putInt(filenameBytes.length); // 先放入4个字节的int，是一个数字，527，,336，代表了这里的文件名有多少个字节
						readFileRequest.put(filenameBytes); // 再把真正的文件名给放入进去
						readFileRequest.rewind();

						// 如果大家去跟着我大数据的课，kafka源码剖析的课去看的话，就会知道
						// kafka里面是用了一模一样的方式，基于一个预定义好的二进制规范，按照规范往ByteBuffer里
						// 写入几个字节的size，几个字节的数据
						channel.write(readFileRequest);

						System.out.println("发送文件下载的请求过去......");

						key.interestOps(SelectionKey.OP_READ);
					}
					// 接收到NIOServer的响应
					else if(key.isReadable()){
						channel = (SocketChannel) key.channel();

						if(fileLength == null) {
							if(fileLengthBuffer == null) {
								fileLengthBuffer = ByteBuffer.allocate(8);
							}
							channel.read(fileLengthBuffer);
							if(!fileLengthBuffer.hasRemaining()) {
								fileLengthBuffer.rewind();
								fileLength = fileLengthBuffer.getLong();
								System.out.println("从服务端返回数据中解析文件大小：" + fileLength);
							}
						}

						if(fileLength != null) {
							if(fileBuffer == null) {
								fileBuffer = ByteBuffer.allocate(
										Integer.valueOf(String.valueOf(fileLength)));
							}
							int hasRead = channel.read(fileBuffer);
							System.out.println("从服务端读取了" + hasRead + " bytes的数据出来到内存中");

							if(!fileBuffer.hasRemaining()) {
								fileBuffer.rewind();
								file = fileBuffer.array();
								System.out.println("最终获取到的文件的大小为" + file.length + " bytes");
								reading = false;
							}
						}
					}
				}
			}

			return file;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(channel != null){
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(selector != null){
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}
