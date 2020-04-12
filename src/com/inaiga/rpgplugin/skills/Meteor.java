package com.inaiga.rpgplugin.skills;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Meteor implements Skill{

	private final static int RADIUS = 3;
	private final static int SPEED = 10;
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
		Thread thread = new Thread(() -> runMeteor(player));
		thread.start();
	}


	//TODO - Apply damage, and add player targeting to the meteor skill
	private void runMeteor(Player player){
		Location startingLocation = player.getLocation().add(player.getEyeLocation().getDirection().multiply(5)).add(0, 15, 0);
		Location endingLocation = player.getLocation().add(player.getEyeLocation().getDirection().multiply(20));
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
	}

}
