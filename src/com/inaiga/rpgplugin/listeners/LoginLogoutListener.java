package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LoginLogoutListener implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("You have joined Kappa");
        System.out.println("Player Join Event");

        PlayerManager.createPlayer(event.getPlayer());
    }

    @EventHandler
    public void PlayerDisconnect(PlayerQuitEvent event) {
        System.out.println("Player Quit Event");

        RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());
        if (rpgPlayer != null) {
            rpgPlayer.saveCharacters();
        }
    }

}
