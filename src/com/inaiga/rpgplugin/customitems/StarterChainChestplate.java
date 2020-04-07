package com.inaiga.rpgplugin.customitems;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

class StarterChainChestplate extends Armor {

    private final int FIRE_DURATION = 5;

	public StarterChainChestplate() {
		super(49);
	}

	@Override
	public void onHitTaken(EntityDamageByEntityEvent event) {
		super.onHitTaken(event);

		if (event.getDamager() instanceof Player) {
			event.getDamager().setFireTicks(20 * FIRE_DURATION);
        } else if (event.getDamager() instanceof Arrow) {
		    ProjectileSource shooter = ((Arrow) event.getDamager()).getShooter();
            if (shooter instanceof LivingEntity){
                ((LivingEntity) shooter).setFireTicks(20 * FIRE_DURATION);
            }
        }
	}
}
