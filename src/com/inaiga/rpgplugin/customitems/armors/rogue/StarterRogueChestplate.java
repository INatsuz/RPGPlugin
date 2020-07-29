package com.inaiga.rpgplugin.customitems.armors.rogue;

import com.inaiga.rpgplugin.customitems.armors.Armor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class StarterRogueChestplate extends Armor {

    //Nota:100 equivale a 5 secs ; 20 faz como se fosse um flash (pretty cool)
    private final int BLIND_EFFECT = 30;   //blind duration

    public StarterRogueChestplate() {
        super(20);
    }

    @Override
    public void onHitTaken(EntityDamageByEntityEvent event) {
        super.onHitTaken(event);

        if (event.getDamager() instanceof Player) {
            ((Player) event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,BLIND_EFFECT,1));
        } else if (event.getDamager() instanceof Arrow) {
            ProjectileSource shooter = ((Arrow) event.getDamager()).getShooter();
            if (shooter instanceof Player){
                ((Player) shooter).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,BLIND_EFFECT,1));
            }
        }
    }

}
