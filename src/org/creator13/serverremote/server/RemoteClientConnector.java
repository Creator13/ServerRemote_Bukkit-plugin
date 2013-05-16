package org.creator13.serverremote.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.creator13.serverremote.MinecraftChat;

public class RemoteClientConnector extends Thread {

	private BufferedInputStream in;
	private BufferedOutputStream out;
	private RemoteServerLoginProtocol RSLP;
	private SocketInformation sInfo;
	
	public RemoteClientConnector(Socket socket) {
		try {
			out = new BufferedOutputStream(socket.getOutputStream());
			in = new BufferedInputStream(socket.getInputStream());
			sInfo = new SocketInformation(socket);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public void run() {
		MinecraftChat.getPluginLogger().info("A new client connected from \"" + sInfo.getIP() + "\"");

	}

}
