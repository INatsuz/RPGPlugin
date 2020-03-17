package com.inaiga.rpgplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener{

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event){
        System.out.println("Player Interact Event");
        System.out.println(event.getItem());
    }

}
