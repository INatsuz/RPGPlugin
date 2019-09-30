package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.CharacterMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equals(CharacterMenu.MENU_NAME)) {
			event.setCancelled(true);
		}
	}

}
