package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Wand implements UsableItem {

	private final int damage;
	private final int range;

	public Wand(int damage, int range) {
		this.damage = damage;
		this.range = range;
	}

	@Override
	public void onHit(PlayerInteractEvent event) {
		RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());

		if (rpgPlayer != null) {
			if (!rpgPlayer.isDoingAbility()) {
				Vector direction = event.getPlayer().getEyeLocation().getDirection().clone().normalize();

				Bat entity = (Bat) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation().add(0, 200, 0), EntityType.BAT);
				entity.setAI(false);
				entity.setSilent(true);
				entity.setInvulnerable(true);
				entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 10, 1, false, false));
				for (int i = 0; i < range; i++) {
					Vector newVector = direction.clone().multiply(i);
					Location currentSpellLocation = event.getPlayer().getEyeLocation().add(newVector);

					if (event.getPlayer().getWorld().getBlockAt(currentSpellLocation).getBlockData().getMaterial().isSolid()) {
						break;
					}

					event.getPlayer().getWorld().spawnParticle(Particle.CRIT, currentSpellLocation, 5, 0.3, 0.3, 0.3, 0.05, null);

					entity.teleport(currentSpellLocation);
					List<Entity> entityList = entity.getNearbyEntities(1, 1, 1);

					entityList.forEach(entityItem -> {
						if (entityItem instanceof Damageable && entityItem != event.getPlayer()) {
							((Damageable) entityItem).damage(damage, event.getPlayer());
						}
					});

				}

				entity.remove();
			}

			rpgPlayer.handleInteraction(false);
		}
	}

	@Override
	public void onUse(PlayerInteractEvent event) {
		RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());

		if (rpgPlayer.getActiveRPGCharacter() != null) {
			if (rpgPlayer.getActiveRPGCharacter().getCharacterRPGClass() == RPGClass.WIZARD) {
				rpgPlayer.handleInteraction(true);
			}
		}
	}

}
