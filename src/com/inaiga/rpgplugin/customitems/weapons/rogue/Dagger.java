package com.inaiga.rpgplugin.customitems.weapons.rogue;

import com.inaiga.rpgplugin.customitems.ClassItem;
import com.inaiga.rpgplugin.customitems.Strikeable;
import com.inaiga.rpgplugin.customitems.Usable;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Dagger extends ClassItem implements Strikeable, Usable {

	@Override
	public void onHit(PlayerInteractEvent event) {}

	@Override
	public void onUse(PlayerInteractEvent event) {

	}

	@Override
	public void onStrike(EntityDamageByEntityEvent event) {

	}

}
