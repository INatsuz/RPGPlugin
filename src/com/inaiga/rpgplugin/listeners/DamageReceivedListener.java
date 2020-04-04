package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.Armor;
import com.inaiga.rpgplugin.customitems.CustomItems;
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

				ItemStack[] armorEquipped = player.getInventory().getArmorContents();
				System.out.println("armor: " + armorEquipped[0]);   //botas
				System.out.println("armor: " + armorEquipped[1]);   //calcas
				System.out.println("armor: " + armorEquipped[2]);   //camisa
				System.out.println("armor: " + armorEquipped[3]);   //capacete

				System.out.println("\nTESTE ITEMMETA: " + armorEquipped[2].getItemMeta().getDisplayName());   //Chestplate

				for (CustomItems value : CustomItems.values()) {
					if (value.getClassInstance() instanceof Armor) {
						if (armorEquipped[2].getItemMeta().getDisplayName().equalsIgnoreCase(value.getName())) {
							System.out.println("Protection Value: " + ((Armor)value.getClassInstance()).getProtection());
							event.setDamage(event.getDamage() - ((Armor)value.getClassInstance()).getProtection());
						}
					}
				}
			}
		}
	}
}
