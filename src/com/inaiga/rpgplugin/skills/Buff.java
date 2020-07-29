package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.utils.TargetingUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Buff implements Skill {

	private static final int RANGE = 20;
	private static final double TARGETING_RADIUS = 0.5;
	private static final int DURATION = 10;

	private static final PotionEffect POTION_EFFECT = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * DURATION, 0, false, false);

	@Override
	public <T> void execute(Player player, T... args) {
		Entity entity = TargetingUtils.getFirstEntityInLine(player, RANGE, TARGETING_RADIUS);

		if (entity == null) {
			player.addPotionEffect(POTION_EFFECT);
		} else if (entity instanceof Player) {
			((Player) entity).addPotionEffect(POTION_EFFECT, true);
		}
	}

}
