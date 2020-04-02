package com.inaiga.rpgplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageReceivedListener implements Listener {

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
