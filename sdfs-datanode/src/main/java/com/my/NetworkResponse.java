package com.my;

import java.nio.ByteBuffer;

/**
 * 网络响应
 *
 */
public class NetworkResponse {

	private String Client;
	private ByteBuffer buffer;

	public ByteBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
	}
	
}
