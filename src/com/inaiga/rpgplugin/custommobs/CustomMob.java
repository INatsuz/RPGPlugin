package com.inaiga.rpgplugin.custommobs;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public interface CustomMob {

	void onSpawn(Entity entity);

	void onHitTaken(EntityDamageByEntityEvent event);

	void onDeath(EntityDeathEvent event);

}
