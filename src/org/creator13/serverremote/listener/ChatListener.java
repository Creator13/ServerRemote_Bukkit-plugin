package org.creator13.serverremote.listener;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.creator13.serverremote.MinecraftChat;

public class ChatListener implements Listener {
	
	private MinecraftChat pluginInstance;
	private Server server;
	
	public ChatListener(MinecraftChat plugin) {
		this.pluginInstance = plugin;
		this.server = plugin.getServer();
		
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (event.getMessage().contains("Hello")) {
			Player playerInstance = event.getPlayer();
			playerInstance.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 1));
			Boolean hardcore = server.isHardcore();
			if (hardcore) server.broadcastMessage("Server is in hardcore mode");
			else server.broadcastMessage("Server isn't in harcore mode");
			playerInstance.sendMessage("WHAHAHAH");
			
		}
		
	}
	
}
