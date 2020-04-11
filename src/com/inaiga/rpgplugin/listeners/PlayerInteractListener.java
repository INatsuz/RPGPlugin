package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			for (CustomItems value : CustomItems.values()) {
				if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(value.getName())) {
					if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						value.onUse(event);
					} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.PHYSICAL) {
						value.onHit(event);
					}

					break;
				}
			}
		}
	}

}
