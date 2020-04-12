package com.inaiga.rpgplugin.skills;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Swift implements Skill {

	private final int MAX_TIME = 7;
	private final int BASE_TIME = 3;
	private final int MAX_DISTANCE = 5;

	@Override
	public <T> void execute(Player player, T... args) {
		List<Entity> entityList = player.getNearbyEntities(MAX_DISTANCE, MAX_DISTANCE, MAX_DISTANCE);

		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * MAX_TIME, 1, false, false), true);
		entityList.forEach(entityItem -> {
			if (entityItem instanceof Player) {
				int duration = (int) Math.round(MAX_TIME - ((MAX_TIME - BASE_TIME) * player.getLocation().distance(entityItem.getLocation()) / MAX_DISTANCE));
				((Player) entityItem).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * duration, 0, false, false), true);
			}
		});
	}

}
