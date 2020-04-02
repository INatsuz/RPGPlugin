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
        System.out.println("Damage Event Received");
        if(event.getDamager() instanceof Player){
            System.out.println("Instance of player confirmed, I guess");
            Player player =(Player) event.getDamager();
            ItemStack handItem = player.getInventory().getItemInMainHand();
            if(handItem.getType() == Material.REDSTONE_BLOCK){
                System.out.println("Redstone block confirmed");
                event.setDamage(50d);
            }
        }
    }
}
