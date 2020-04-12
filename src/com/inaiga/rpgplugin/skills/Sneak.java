package com.inaiga.rpgplugin.skills;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Sneak implements Skill {

	@Override
	public <T> void execute(Player player, T... args) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 5, 0, false, false), true);
	}

}
