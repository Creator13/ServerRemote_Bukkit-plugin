package org.creator13.serverremote;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.creator13.serverremote.listener.ChatListener;
import org.creator13.serverremote.server.RemoteServer;

public class MinecraftChat extends JavaPlugin {

	private static Logger logger;
	private RemoteServer remServer;
	
	public void onEnable() {
		addCommandExecutor(new MCChatCommandExecutor(this));
		addListeners();
		setupRemoteServer();
		logger = getLogger();
		logger.info("MinecraftChat has been enabled succesfully!");
		
	}
	
	public void onDisable() {
		logger.info("MinecraftChat has been disabled succesfully!");
		remServer.stop();
		
	}
	
	private void addCommandExecutor(CommandExecutor ce) {
		ArrayList<String> aliasList = new ArrayList<String>();
		aliasList.add("mcc");
		getCommand("mcchat").setAliases(aliasList);
		getCommand("mcchat").setExecutor(ce);
		getCommand("stopremote").setExecutor(ce);
		
	}
	
	private void addListeners() {
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		
	}
	
	private void setupRemoteServer() {
		new Thread(remServer = new RemoteServer(getServer().getMaxPlayers(), this)).start();
		
	}
	
	public static Logger getPluginLogger() {
		return logger;
		
	}
	
	public RemoteServer getRemoteServer() {
		return remServer;
		
	}
	
}
