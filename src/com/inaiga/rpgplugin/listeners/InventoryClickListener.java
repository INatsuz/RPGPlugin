package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.MenuType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	//Cancels clicks on the menus
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		for (MenuType menuType : MenuType.values()) {
			if (event.getView().getTitle().equals(menuType.getName())) {
				event.setCancelled(true);
			}
		}
	}

}