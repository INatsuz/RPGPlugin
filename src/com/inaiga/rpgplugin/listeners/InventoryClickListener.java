package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.menus.Menu;
import com.inaiga.rpgplugin.menus.MenuManager;
import java.util.HashMap;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

	//Cancels clicks on the menus
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		HashMap<Player, Menu> hashMap = MenuManager.getOpenMenus();

		HumanEntity humanEntity = event.getWhoClicked();
		if (humanEntity instanceof Player) {
			Menu menu = hashMap.get(humanEntity);
			if (menu != null) {
				menu.handleClick(event);
				event.setCancelled(true);
			}
		}

	}

}