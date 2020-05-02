package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.utils.TargetingUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Heal implements Skill {

	private static final int HEAL_AMOUNT = 6;
	private static final int RANGE = 20;
	private static final double RADIUS = 0.5;

	@Override
	public <T> void execute(Player player, T... args) {
		Entity target = TargetingUtils.getFirstEntityInLine(player, RANGE, RADIUS);

		if (target instanceof Player) {
			Player targetPlayer = ((Player) target);
			targetPlayer.setHealth(Math.min(targetPlayer.getHealth() + HEAL_AMOUNT, 20));
		} else if (target == null) {
			player.setHealth(Math.min(player.getHealth() + HEAL_AMOUNT, 20));
		}
	}

}
