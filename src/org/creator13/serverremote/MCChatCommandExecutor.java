package org.creator13.serverremote;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.creator13.serverremote.server.RemoteServer;

public class MCChatCommandExecutor implements CommandExecutor {

	private MinecraftChat plugin;
	
	public MCChatCommandExecutor(MinecraftChat plugin) {
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
							 String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mcchat")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.BLUE + "You executed the " + ChatColor.RED + "mcchat " + ChatColor.BLUE + "command");
				return true;
				
			}
		}
		else if (cmd.getName().equalsIgnoreCase("send")) {
			
			
		}
		else if (cmd.getName().equalsIgnoreCase("stopRemote")) {
			RemoteServer server = plugin.getRemoteServer();
			if (!server.isClosed()) {
				server.stop();
				
			}
			else {
				plugin.getLogger().warning("Couldn't stop remote server, because none was running!");
				
			}
			
			return true;
			
		}
//		else if (cmd.getName().equalsIgnoreCase("startRemote")) {
//			RemoteServer server = plugin.getRemoteServer();
//			if (server.isClosed() || server == null) {
//				
//				
//			}
//			
//		}
		
		return false;
	}

	
	
}
