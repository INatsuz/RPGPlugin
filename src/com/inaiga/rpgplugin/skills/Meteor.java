package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.MainClass;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Meteor implements Skill {

	private final static int RADIUS = 3;
	private final static int SPEED = 10;
	private final static int RANGE = 50;
	private final static int DAMAGE = 60;

	private ArrayList<Vector> sphereVectors = new ArrayList<>();

	public Meteor() {
		Vector vector;
		for (int x = -RADIUS; x <= RADIUS; x++) {
			for (int y = -RADIUS; y <= RADIUS; y++) {
				for (int z = -RADIUS; z <= RADIUS; z++) {
					vector = new Vector(x, y, z);
					if (vector.length() - RADIUS <= 0.5) {
						sphereVectors.add(vector);
					}
				}
			}
		}
	}

	@Override
	public <T> void execute(Player player, T... args) {
		Vector playerDirection = player.getLocation().getDirection();
		Location startingLocation = player.getLocation().add(playerDirection.multiply(5)).add(0, 15, 0);
		Location endingLocation = null;

		Bat entity = (Bat) player.getWorld().spawnEntity(player.getLocation().add(0, 200, 0), EntityType.BAT);
		entity.setAI(false);
		entity.setSilent(true);
		entity.setInvulnerable(true);
		entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 10, 1, false, false));

		Location targetingLocation = player.getLocation();
		for (int i = 0; i < RANGE; i++) {
			entity.teleport(targetingLocation.add(playerDirection));
			List<Entity> nearbyEntities = entity.getNearbyEntities(1, 1, 1);

			if (!nearbyEntities.isEmpty()) {
				endingLocation = nearbyEntities.get(0).getLocation();
				System.out.println("Found location");
				if (nearbyEntities.get(0) instanceof Player) {
					System.out.println("Meteor targeting player: " + nearbyEntities.get(0).getName());
				}
				break;
			}
		}
		if (endingLocation == null) {
			endingLocation = player.getLocation().add(player.getEyeLocation().getDirection().multiply(20));
		}

		Location finalEndingLocation = endingLocation;
		Thread thread = new Thread(() -> runMeteorAnimation(player, startingLocation, finalEndingLocation, entity));
		thread.start();
	}


	//TODO - Apply damage, and add player targeting to the meteor skill
	private void runMeteorAnimation(Player player, Location startingLocation, Location endingLocation, Entity entity) {
		System.out.println("Hello from another thread");
		Vector direction = endingLocation.clone().subtract(startingLocation).toVector().normalize();

		for (int i = 0; i <= startingLocation.distance(endingLocation); i++) {
			long initialTime = System.currentTimeMillis();
			Location center = startingLocation.clone().add(direction.clone().multiply(i));

			for (Vector vector : sphereVectors) {
				player.getWorld().spawnParticle(Particle.FLAME, center.clone().add(vector), 1, 0.1, 0.1, 0.1, 0.05, null);
			}

			try {
				Thread.sleep(1000 / SPEED - (System.currentTimeMillis() - initialTime));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), () -> damageNearbyEntities(player, entity), 0);
	}

	private void damageNearbyEntities(Player player, Entity entity){
		List<Entity> targetsToDamage = entity.getNearbyEntities(RADIUS, RADIUS, RADIUS);
		for (Entity nearbyEntity : targetsToDamage) {
			if (nearbyEntity instanceof Damageable) {
				((Damageable) nearbyEntity).damage(DAMAGE, player);
			}
		}

		entity.remove();
	}
}
