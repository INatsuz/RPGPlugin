package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.Menu;
import com.inaiga.rpgplugin.menus.MenuManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	//Cancels clicks on the menus
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		for (Menu openMenu : MenuManager.getOpenMenus()) {
			if (openMenu.getMenuInventory() == event.getInventory()) {
				System.out.println("This is definitely the inventory you're looking for 🌎");
				event.setCancelled(true);
				openMenu.handleClick(event);
			}
		}
	}

}