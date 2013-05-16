package org.creator13.serverremote.server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.creator13.serverremote.MinecraftChat;

/**
 * The RemoteServer class containing methods to run this server. It was built
 * for the Bukkit© platform, and it should only be used to run on a bukkit 
 * server.
 * <p>
 * Uses <code>RemoteClientConnection</code> and <code>RemoteClientConnector</code> 
 * for client handling.
 * 
 * @author Casper van Battum (Creator13)
 * @version 1.0
 */
public class RemoteServer implements Runnable {

	private ServerSocket remoteServer;
	
	private int maxConnections;
	private int bufferSlots = 1;
	private int currentConnections = 0;
	/** <code>true</code> while accepting any connections. Intitial value is 
	 *  <code>false</code>. Must be true, for the server to be able to accept
	 *  a connection. To stop the server, first */
	private boolean active = false;
	/**	Array holding all connectors, currently running. If the connector
	 * 	accepts this connection, the connection will be passed over in the
	 *  <code>connectedClients</code> array. If the connection came to ping,
	 *  it will return a <code>true</code> value and stop the connection, and 
	 *  so removed from this array. If the connection was not accepted, the
	 *  connection is directly removed, before the client could send eventual 
	 *  malicious data. */
	private RemoteClientConnector[] connectorArray;
	/** Array holding all running, accepted connections. */
	private RemoteClientConnection[] connectedClients;
	
	private MinecraftChat plugin;
	
	/**
	 * Main constructor. Needs to be called, in order to function correctly.
	 * 
	 * @param maxConnections The maximal number of people able to connect to
	 * 						 the remote server.
	 * @param plugin		 The parent plugin.
	 */
	public RemoteServer(int maxConnections, MinecraftChat plugin) {
		this.maxConnections = maxConnections + bufferSlots;
		this.plugin = plugin;
		
		connectorArray = new RemoteClientConnector[maxConnections];
		
	}
	
	@Override
	public void run() {
		startServer();
		
	}
	
	private void startServer() {
		try {
			remoteServer = new ServerSocket(25566, maxConnections);
			plugin.getLogger().info("Remote Server started on new thread.");
			while (active) {
				try {
					whileRunning();
					
				}
				catch(EOFException eofException) {
					plugin.getLogger().info("Server ended the connection");
					
				}
				
			}
		
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
			
		}
		
	}
	
	public void stop() {
		plugin.getLogger().info("Attempting to stop remote server.");

		active = false;
		
		try {
			remoteServer.close();
					
		} catch (IOException e) {
			plugin.getLogger().severe("Remote server has crashed while " +
									  "attempting to stop the server: ");
			e.printStackTrace();
			return;
				
		}
		
		plugin.getLogger().info("Remote server stopped succesfully");
		
	}

	private void whileRunning() throws IOException {
		while (active) {
			Socket clientSocket = remoteServer.accept();
			try {
				connectorArray[currentConnections] = 
						new RemoteClientConnector(clientSocket);
				connectorArray[currentConnections].start();
				currentConnections++;
				
			}
			catch (IndexOutOfBoundsException e) {
				plugin.getLogger().info("Client attempted to connect, " +
										"but server was full");
				
			}
			
		}
		
	}
	
	/**
	 * Closes the server, so that no client can connect to it.
	 */
	private void closeServer() {
		try {
			remoteServer.close();
			
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
			
		}
		
	}
	
	/**
	 * @return The boolean value indicating if the server is closed.
	 */
	public boolean isClosed() {
		return remoteServer.isClosed();
		
		
	}
	
	/**
	 * @return All connections currently connected to the remote server, in the
	 * 		   form of a <code>RemoteClientConnection</code> array.
	 * @see RemoteClientConnection
	 */
	public RemoteClientConnection[] getConnections() {
		return this.connectedClients;
		
	}

}
