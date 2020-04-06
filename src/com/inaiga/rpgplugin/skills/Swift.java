package com.inaiga.rpgplugin.skills;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Swift implements Skill {

    @Override
    public <T> void execute(T... args) {
        Player player = (Player) args[0];

        List<Entity> entityList = player.getNearbyEntities(5, 5, 5);

        entityList.forEach(entityItem -> {
            if(entityItem instanceof Player) {
                ((Player) entityItem).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5, 1, false, false));
            }
        });
    }

}
