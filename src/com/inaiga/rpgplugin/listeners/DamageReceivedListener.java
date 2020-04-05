package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.Armor;
import com.inaiga.rpgplugin.customitems.CustomItems;
import org.bukkit.Bukkit;
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
			if (event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();

				double totalProtection = 0;
				ItemStack[] armorEquipped = player.getInventory().getArmorContents();
				for (ItemStack armorPiece : armorEquipped) {
					if (armorPiece != null) {
						for (CustomItems value : CustomItems.values()) {
							if (value.getClassInstance() instanceof Armor) {
								if (armorPiece.getItemMeta().getDisplayName().equalsIgnoreCase(value.getName())) {
									totalProtection += ((Armor) value.getClassInstance()).getProtection();
								}
							}
						}
					}
				}

				double damage = event.getDamage() - totalProtection;
				System.out.println("Damage is supposed to be: " + damage);
				player.sendMessage("Damage is supposed to be: " + damage);
				if (((Player) event.getEntity()).getHealth() - damage <= 0) {
					((Player) event.getEntity()).setHealth(Math.max(((Player) event.getEntity()).getHealth() - damage, 1));
					event.setDamage(Integer.MAX_VALUE);
				} else {
					event.setDamage(0);
					((Player) event.getEntity()).setHealth(Math.max(((Player) event.getEntity()).getHealth() - damage, 0));
				}
			}
		}
	}
}
