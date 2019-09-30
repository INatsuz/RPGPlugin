package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.characters.CharacterMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		if (event.getInventory().getName().equals(CharacterMenu.menuInventory.getName())) {
			event.setCancelled(true);
		}
	}

}
