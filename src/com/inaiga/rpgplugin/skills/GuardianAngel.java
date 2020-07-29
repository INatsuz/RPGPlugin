package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.MainClass;
import com.inaiga.rpgplugin.utils.TargetingUtils;
import java.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GuardianAngel implements Skill {

	private static final int RANGE = 20;
	private static final double TARGETING_RADIUS = 0.5;
	private static final int DURATION = 3;

	private static final Timer timer = new Timer();

	@Override
	public <T> void execute(Player player, T... args) {
		Entity entity = TargetingUtils.getFirstEntityInLine(player, RANGE, TARGETING_RADIUS);

		if (entity == null) {
			player.setInvulnerable(true);
		} else if (entity instanceof Player) {
			entity.setInvulnerable(true);
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), () -> {
			resetVulnerability(entity instanceof Player ? entity : player);
		}, 20 * DURATION);
	}

	private void resetVulnerability(Entity entity) {
		entity.setInvulnerable(false);
	}

}
