package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.Menu;
import com.inaiga.rpgplugin.menus.MenuManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	//Cancels clicks on the menus
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		HashMap hashMap = MenuManager.getOpenMenus();
		Iterator iterator = hashMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Player, Menu> pair = (Map.Entry<Player, Menu>) iterator.next();

			if (pair.getValue().getMenuInventory() == event.getInventory()) {
				System.out.println("This is definitely the inventory you're looking for 🌎");
				event.setCancelled(true);
				pair.getValue().handleClick(event);
			}
		}
	}

}