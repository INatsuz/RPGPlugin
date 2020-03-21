package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.CustomItems;
import com.inaiga.rpgplugin.skills.Skills;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event) {
		if (event != null) {
			if (event.getItem() != null) {
				for (CustomItems value : CustomItems.values()) {
					if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(value.getName())) {
						if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
							value.onUse(event);
						} else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
							value.onHit(event);
						}

						break;
					}
				}
			}
			if (event.getPlayer().isSneaking() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				Skills.executeSkill(Skills.SNEAK, event.getPlayer());
			}

			System.out.println("Player Interact Event");
		}
	}

}
