package com.my;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * 客户端的一个NIOClient，负责跟数据节点进行网络通信
 *
 */
public class NioClient {
	
	private static NetworkManager networkManager = new NetworkManager();
	
	/**
	 * 发送一个文件过去
	 */
	public static Boolean sendFile(FileInfo fileInfo, Host host, ResponseCallback callback) {
		// 按理说，这里应该先根据hostname来检查一下，跟对方机器的连接是否建立好了
		// 如果还没有建立好，那么就直接在这里建立连接
		// 建立好连接之后，就应该把连接给缓存起来，以备下次来进行使用
		
		// 如果此时还没跟那个数据节点建立好连接
		if(!networkManager.maybeConnect(host.getHostname(), host.getNioPort())) {
			return false;
		}
		
		NetworkRequest request = createSendFileRequest(fileInfo, host, callback);
		networkManager.sendRequest(request); 
		
		return true;
	}
	
	/**
	 * 读取文件
	 */
	public static byte[] readFile(Host host, String filename, Boolean retry) throws Exception {
		if(!networkManager.maybeConnect(host.getHostname(), host.getNioPort())) {
			if(retry) {
				throw new Exception();
			}
		}
		
		NetworkRequest request = createReadFileRequest(host, filename, null);
		networkManager.sendRequest(request); 

		// 同步等待响应
		NetworkResponse response = networkManager.waitResponse(request.getId());
		
		if(response.error()) {
			if(retry) {
				throw new Exception();
			}
		}
		
		return response.getBuffer().array();
	}
	
	/**
	 * 构建一个发送文件的网络请求
	 * @return
	 */
	private static NetworkRequest createSendFileRequest(FileInfo fileInfo, Host host,
														ResponseCallback callback) {
		NetworkRequest request = new NetworkRequest();
		
		ByteBuffer buffer = ByteBuffer.allocate(
				NetworkRequest.REQUEST_TYPE + 
				NetworkRequest.FILENAME_LENGTH + 
				fileInfo.getFilename().getBytes().length + 
				NetworkRequest.FILE_LENGTH + 
				(int)fileInfo.getFileLength());  
		buffer.putInt(NetworkRequest.REQUEST_SEND_FILE); 
		buffer.putInt(fileInfo.getFilename().getBytes().length); 
		buffer.put(fileInfo.getFilename().getBytes()); 
		buffer.putLong(fileInfo.getFileLength()); 
		buffer.put(fileInfo.getFile());
		buffer.rewind(); 
		
		request.setId(UUID.randomUUID().toString()); 
		request.setHostname(host.getHostname());
		request.setRequestType(NetworkRequest.REQUEST_SEND_FILE); 
		request.setIp(host.getIp());  
		request.setNioPort(host.getNioPort()); 
		request.setBuffer(buffer); 
		request.setNeedResponse(false); 
		request.setCallback(callback); 
		
		return request;
	}
	
	/**
	 * 构建一个发送文件的网络请求
	 * @return
	 */
	private static NetworkRequest createReadFileRequest(Host host, String filename,
														ResponseCallback callback) {
		NetworkRequest request = new NetworkRequest();
		
		byte[] filenameBytes = filename.getBytes();
		
		ByteBuffer buffer = ByteBuffer.allocate(
				NetworkRequest.REQUEST_TYPE + 
				NetworkRequest.FILENAME_LENGTH + 
				filenameBytes.length); 
		buffer.putInt(NetworkRequest.REQUEST_READ_FILE);
		buffer.putInt(filenameBytes.length); 
		buffer.put(filenameBytes); 
		buffer.rewind();
		
		request.setId(UUID.randomUUID().toString()); 
		request.setHostname(host.getHostname());
		request.setRequestType(NetworkRequest.REQUEST_READ_FILE);  
		request.setIp(host.getIp());  
		request.setNioPort(host.getNioPort()); 
		request.setBuffer(buffer); 
		request.setNeedResponse(true); 
		request.setCallback(callback); 
		
		return request;
	}
	
}
