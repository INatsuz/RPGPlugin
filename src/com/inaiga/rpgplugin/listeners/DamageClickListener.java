package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DamageClickListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player player =(Player) event.getDamager();
            ItemStack handItem= player.getItemOnCursor();
            if(handItem.getType() == Material.REDSTONE_BLOCK){
                event.setDamage((double) 50);
            }
        }
    }
}
