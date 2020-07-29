package com.inaiga.rpgplugin.utils;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class TargetingUtils {

	public static Entity spawnTargetingEntity(Location location) {
		Bat entity = (Bat) location.getWorld().spawnEntity(location.add(0, 200, 0), EntityType.BAT);
		entity.setAI(false);
		entity.setSilent(true);
		entity.setInvulnerable(true);
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 10, 1, false, false));
		entity.teleport(location);

		return entity;
	}

	public static Entity spawnTargetingEntity(Player player) {
		Bat entity = (Bat) player.getWorld().spawnEntity(player.getLocation().add(0, 200, 0), EntityType.BAT);
		entity.setAI(false);
		entity.setSilent(true);
		entity.setInvulnerable(true);
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 10, 1, false, false));
		entity.teleport(player.getEyeLocation());

		return entity;
	}

	public static Entity[] getEntitiesInLine(Player player, int length, double radius, boolean hasBlockCollision) {
		Entity entity = spawnTargetingEntity(player);

		ArrayList<Entity> entitiesInLine = new ArrayList<>();

		Vector direction = player.getEyeLocation().getDirection();
		for (int i = 0; i < length; i++) {
			entity.teleport(entity.getLocation().add(direction));
			if (hasBlockCollision) {
				if (!entity.getWorld().getBlockAt(entity.getLocation()).isPassable()) {
					entity.remove();
					return (Entity[]) entitiesInLine.toArray();
				}
			}

			entitiesInLine.addAll(entity.getNearbyEntities(radius, radius, radius));
		}

		entity.remove();

		return (Entity[]) entitiesInLine.toArray();
	}

	public static <T> ArrayList<Entity> getEntitiesInLine(Player player, int length, double radius, boolean hasBlockCollision, Particle particle, int particleAmount, double v, double v1, double v2, double v3, T data) {
		Entity entity = spawnTargetingEntity(player);

		ArrayList<Entity> entitiesInLine = new ArrayList<>();

		Vector direction = player.getEyeLocation().getDirection();
		for (int i = 0; i < length; i++) {
			entity.teleport(entity.getLocation().add(direction));
			if (hasBlockCollision) {
				if (!entity.getWorld().getBlockAt(entity.getLocation()).isPassable()) {
					entity.remove();
					return entitiesInLine;
				}
			}
			entity.getWorld().spawnParticle(particle, entity.getLocation(), particleAmount, v, v1, v2, v3, data);

			entitiesInLine.addAll(entity.getNearbyEntities(radius, radius, radius));
		}

		entity.remove();

		return entitiesInLine;
	}

	public static Entity getFirstEntityInLine(Player player, int length, double radius) {
		Entity entity = spawnTargetingEntity(player);

		Vector direction = player.getEyeLocation().getDirection();
		for (int i = 0; i < length; i++) {
			entity.teleport(entity.getLocation().add(direction));
			if (!entity.getNearbyEntities(radius, radius, radius).isEmpty()) {
				for (Entity nearbyEntity : entity.getNearbyEntities(radius, radius, radius)) {
					if (nearbyEntity != player) {
						entity.remove();
						return nearbyEntity;
					}
				}
			}
		}
		entity.remove();

		return null;
	}

}
