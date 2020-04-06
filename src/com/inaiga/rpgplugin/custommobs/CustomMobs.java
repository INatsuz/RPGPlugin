package com.inaiga.rpgplugin.custommobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import javax.annotation.Nonnull;

public enum CustomMobs {
	AZAROTH_ZOMBIE("AZAROTH ZOMBIE", new AzarothZombie(), EntityType.ZOMBIE);

	private final String name;
	private final CustomMob classInstance;
	private final EntityType entityType;

	CustomMobs(String name, CustomMob classInstance, EntityType entityType) {
		this.name = name;
		this.classInstance = classInstance;
		this.entityType = entityType;
	}

	public String getName() {
		return name;
	}

	public CustomMob getClassInstance() {
		return classInstance;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public static void spawnCustomMob(@Nonnull CustomMobs customMob, @Nonnull Location location) {
		Entity entity = location.getWorld().spawnEntity(location, customMob.getEntityType());
		entity.setCustomName(ChatColor.RED + customMob.getName());
		entity.setCustomNameVisible(true);
		if (entity instanceof Mob) {
			((Mob) entity).setRemoveWhenFarAway(false);
			((Mob) entity).setLootTable(null);
		}

		customMob.getClassInstance().onSpawn(entity);
	}

}
