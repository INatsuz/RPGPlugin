package com.inaiga.rpgplugin.custommobs;

import com.inaiga.rpgplugin.customitems.CustomItems;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class AzarothZombie implements CustomMob {

	@Override
	public void onSpawn(Entity entity) {
		Zombie zombie = (Zombie) entity;
		zombie.setAdult();
		if (zombie.getEquipment() != null) {
			zombie.getEquipment().setChestplate(CustomItems.buildCustomItem(CustomItems.STARTER_CHAIN_CHESTPLATE));
		}
		zombie.setLootTable(null);
		if (zombie.getAttribute(Attribute.GENERIC_ARMOR) != null) {
			zombie.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
		}
		if (zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
			zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1000);
		}
		if (zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED) != null) {
			zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.5);
		}
		zombie.setHealth(1000);
	}

	@Override
	public void onHitTaken(EntityDamageByEntityEvent event) {

	}

	@Override
	public void onDeath(EntityDeathEvent event) {

	}

}
