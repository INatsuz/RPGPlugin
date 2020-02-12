package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.MenuType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		for (MenuType menuType : MenuType.values()) {
			System.out.println(event.getView().getTitle());
			if (event.getView().getTitle().equals(menuType.getName())) {
				System.out.println("Inventory Click Cancelled");
				event.setCancelled(true);
			}
		}

	}

}
