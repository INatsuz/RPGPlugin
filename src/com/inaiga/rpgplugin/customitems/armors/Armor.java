package com.inaiga.rpgplugin.customitems.armors;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Armor implements WearableItem {

	public final int protection;

	//Constructor for armor objects
	public Armor(int protection) {
		this.protection = protection;
	}

	@Override
	public void onHitTaken(EntityDamageByEntityEvent event) {

	}

	public int getProtection() {
		return protection;
	}

}
