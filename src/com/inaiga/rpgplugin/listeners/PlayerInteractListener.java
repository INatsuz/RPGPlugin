package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.CustomItemManager;
import com.inaiga.rpgplugin.customitems.CustomItems;
//import com.inaiga.rpgplugin.skills.Skills;
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
		}
<<<<<<< HEAD
//		if (event.getPlayer().isSneaking() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
//			Skills.executeSkill(Skills.SNEAK, event.getPlayer());
//		}

		System.out.println("Player Interact Event");
=======
>>>>>>> testing
	}

}
