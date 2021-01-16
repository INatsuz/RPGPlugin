package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.CustomItemManager;
import com.inaiga.rpgplugin.customitems.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event) {
		if (event != null) {
			if (event.getItem() != null) {
				CustomItems item = CustomItemManager.getCustomItemByName(event.getItem().getItemMeta().getDisplayName());
				if (item != null) {
					if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						item.onUse(event);
					} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
						item.onHit(event);
					}
				}
			}

//			for (CustomItems value : CustomItems.values()) {
//				if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(value.getName())) {
//					if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
//						value.onUse(event);
//					} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
//						value.onHit(event);
//					}
//
//					break;
//				}
//			}
		}
	}

}
