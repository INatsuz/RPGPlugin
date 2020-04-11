package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.MenuManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void InventoryClose(InventoryCloseEvent event){
        MenuManager.closeOpenMenuForPlayer((Player)event.getPlayer());
    }

}
