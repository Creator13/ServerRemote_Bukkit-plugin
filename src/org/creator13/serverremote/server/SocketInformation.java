package org.creator13.serverremote.server;

import java.net.InetAddress;
import java.net.Socket;

public class SocketInformation {

	private String IP;
	private int port;
	private int localPort;
	private InetAddress inetAddress;
	
	public SocketInformation(Socket socket) {
		port = socket.getPort();
		localPort = socket.getLocalPort();
		inetAddress = socket.getInetAddress();
		IP = inetAddress.getHostAddress();
		
	}
	
	public String getIP() {
		return IP;
		
	}
	
	public int getPort() {
		return port;
		
	}
	
	public int getLocalPort() {
		return localPort;
		
	}
	
	public InetAddress getInetAddress() {
		return inetAddress;
		
	}
	
}
