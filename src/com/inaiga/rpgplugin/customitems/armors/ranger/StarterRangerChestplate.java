package com.inaiga.rpgplugin.customitems.armors.ranger;

import com.inaiga.rpgplugin.customitems.armors.Armor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StarterRangerChestplate extends Armor {

	int speed = 1;
	float knockback = 0.5f;

	public StarterRangerChestplate() {
		super(10);
	}

	@Override
	public void onHitTaken(EntityDamageByEntityEvent event) {
		super.onHitTaken(event);

		((Player) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, speed, 1));

		if (event.getDamager() instanceof Player) {
			Entity attacker = event.getDamager();
			Entity attacked = event.getEntity();

			attacker.setVelocity(attacker.getLocation().getDirection().normalize().multiply(-1 * knockback));
		}
	}
}
