package com.inaiga.rpgplugin.customitems.armors.wizard;

import com.inaiga.rpgplugin.customitems.armors.Armor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class StarterWizardChestplate extends Armor {
    public StarterWizardChestplate() {
        super(10);
    }

    @Override
    public void onHitTaken(EntityDamageByEntityEvent event) {
        super.onHitTaken(event);

        if (event.getDamager() instanceof Player) {
            ((Player) event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.HARM,1,1));
        } else if (event.getDamager() instanceof Arrow) {
            ProjectileSource shooter = ((Arrow) event.getDamager()).getShooter();
            if (shooter instanceof Player){
                ((Player) event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.HARM,1,1));
            }
        }
    }

}
