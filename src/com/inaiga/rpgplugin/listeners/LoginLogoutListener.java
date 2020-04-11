package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.MenuManager;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LoginLogoutListener implements Listener {

    //Creates an RPGPlayer when he logs in
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("You have joined");

        PlayerManager.createPlayer(event.getPlayer());
    }

    //Saves an RPGPlayer when he logs out
    @EventHandler
    public void PlayerDisconnect(PlayerQuitEvent event) {

        RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());
        if (rpgPlayer != null) {
            rpgPlayer.saveCharacters();
        }
        PlayerManager.removePlayer(event.getPlayer());
        MenuManager.closeOpenMenuForPlayer(event.getPlayer());
    }
}