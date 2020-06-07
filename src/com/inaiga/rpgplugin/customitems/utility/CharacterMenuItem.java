package com.inaiga.rpgplugin.customitems.utility;

import com.inaiga.rpgplugin.customitems.weapons.UsableItem;
import com.inaiga.rpgplugin.menus.MenuManager;
import com.inaiga.rpgplugin.menus.MenuType;
import org.bukkit.event.player.PlayerInteractEvent;

public class CharacterMenuItem implements UsableItem {

	@Override
	public void onHit(PlayerInteractEvent event) {

	}

	@Override
	public void onUse(PlayerInteractEvent event) {
		MenuManager.openMenuForPlayer(event.getPlayer(), MenuType.CHARACTER_SELECTION_MENU);
		event.getPlayer().sendMessage("Opening Character Menu");
	}

}
