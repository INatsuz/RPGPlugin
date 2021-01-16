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
