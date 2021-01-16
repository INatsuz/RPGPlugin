package com.inaiga.rpgplugin.customitems.weapons.wizard;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.customitems.ClassItem;
import com.inaiga.rpgplugin.customitems.Usable;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import com.inaiga.rpgplugin.utils.TargetingUtils;
import java.util.ArrayList;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;

public class Wand extends ClassItem implements Usable {

	private final int DAMAGE;
	private final int RANGE;
	private final double TARGETING_RADIUS = 1;

	public Wand(int damage, int range) {
		super(RPGClass.WIZARD);

		this.DAMAGE = damage;
		this.RANGE = range;
	}

	@Override
	public void onHit(PlayerInteractEvent event) {
		RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());

		if (rpgPlayer != null) {
			if (!isPlayerAllowedToUseItem(rpgPlayer)) return;

			if (!rpgPlayer.isDoingAbility()) {
				ArrayList<Entity> targetEntities = TargetingUtils.getEntitiesInLine(event.getPlayer(), RANGE, TARGETING_RADIUS, true, Particle.CRIT, 5, 0.3, 0.3, 0.3, 0.05, null);
				for (Entity targetEntity : targetEntities) {
					if (targetEntity instanceof Damageable && targetEntity != event.getPlayer()) {
						((Damageable) targetEntity).damage(DAMAGE, event.getPlayer());
					}
				}
//
//				Vector direction = event.getPlayer().getEyeLocation().getDirection().clone().normalize();
//
//				Bat entity = (Bat) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation().add(0, 200, 0), EntityType.BAT);
//				entity.setAI(false);
//				entity.setSilent(true);
//				entity.setInvulnerable(true);
//				entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 10, 1, false, false));
//				for (int i = 0; i < RANGE; i++) {
//					Vector newVector = direction.clone().multiply(i);
//					Location currentSpellLocation = event.getPlayer().getEyeLocation().add(newVector);
//
//					if (event.getPlayer().getWorld().getBlockAt(currentSpellLocation).getBlockData().getMaterial().isSolid()) {
//						break;
//					}
//
//					event.getPlayer().getWorld().spawnParticle(Particle.CRIT, currentSpellLocation, 5, 0.3, 0.3, 0.3, 0.05, null);
//
//					entity.teleport(currentSpellLocation);
//					List<Entity> entityList = entity.getNearbyEntities(1, 1, 1);
//
//					entityList.forEach(entityItem -> {
//						if (entityItem instanceof Damageable && entityItem != event.getPlayer()) {
//							((Damageable) entityItem).damage(DAMAGE, event.getPlayer());
//						}
//					});
//
//				}
//
//				entity.remove();
			}

			rpgPlayer.handleInteraction(false);
		}
	}

	@Override
	public void onUse(PlayerInteractEvent event) {
		RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());

		if (rpgPlayer != null) {
			if (!isPlayerAllowedToUseItem(rpgPlayer)) return;

			rpgPlayer.handleInteraction(true);
		}
	}

}
