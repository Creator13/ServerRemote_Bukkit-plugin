package org.creator13.serverremote.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class RemoteConnectionHandler implements Runnable {

	private String name;
	
	private Socket connection = null;
	private ObjectOutputStream out;
	
	public RemoteConnectionHandler(Socket connection) {
		this.connection = connection;
		
	}
	
	@Override
	public void run() {
		try {
			setupStreams();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			
		}
		
		Logger.getLogger("Minecraft").info("New client connected from " + connection.getInetAddress().getHostAddress());
		

	}
	
	private void setupStreams() throws IOException {
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		
	}
	
	public void closeConnection() {
		try {
			out.close();
			connection.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}

}
