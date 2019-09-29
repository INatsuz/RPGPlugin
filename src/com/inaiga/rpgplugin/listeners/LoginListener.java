package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player p =  event.getPlayer();
        PlayerManager.createPlayer(p);
    }

}
