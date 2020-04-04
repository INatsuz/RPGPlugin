package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.CustomItems;
import net.minecraft.server.v1_14_R1.EntityDamageSource;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageReceivedListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        /*if(event.getDamager() instanceof Player){
            Player player =(Player) event.getDamager();
            ItemStack handItem = player.getInventory().getItemInMainHand();
            if(handItem.getType() == Material.REDSTONE_BLOCK){
                event.setDamage(50d);
            }
        }*/
        if (event != null) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                ItemStack[] ArmorEquipped = player.getInventory().getArmorContents();
                System.out.println("armor: " + ArmorEquipped[0]);   //botas
                System.out.println("armor: " + ArmorEquipped[1]);   //calcas
                System.out.println("armor: " + ArmorEquipped[2]);   //camisa
                System.out.println("armor: " + ArmorEquipped[3]);   //capacete
                System.out.println("\nTESTE ITEMMETA: " + ArmorEquipped[2].getItemMeta().getDisplayName());   //capacete
                for (CustomItems value : CustomItems.values()) {
                    if (ArmorEquipped[2].getItemMeta().getDisplayName().equalsIgnoreCase(value.getName())) {
                        System.out.println("REDUCAO: " + value.getArmorMultiplier());
                        event.setDamage(value.getArmorMultiplier());
                    }
                }
            }
        }
    }
}
