package com.inaiga.rpgplugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player p =  event.getPlayer();
        p.sendMessage(ChatColor.AQUA + "This is a test!");
    }

}
