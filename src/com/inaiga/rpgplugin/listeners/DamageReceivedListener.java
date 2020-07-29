package com.inaiga.rpgplugin.listeners;

import com.inaiga.rpgplugin.customitems.armors.Armor;
import com.inaiga.rpgplugin.customitems.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageReceivedListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
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
									value.onHitTaken(event);
								}
							}
						}
					}
				}

				double damage = event.getDamage() - totalProtection;
				System.out.println("Damage is supposed to be: " + damage);
				player.sendMessage("Damage is supposed to be: " + damage);
				if (damage > 0) {
					if (((Player) event.getEntity()).getHealth() - damage <= 0) {
						((Player) event.getEntity()).setHealth(1);
						player.sendMessage("Wouldve killed you! Dmg: " + damage + " / Health Set to: " + ((Player) event.getEntity()).getHealth());
						event.setDamage(Integer.MAX_VALUE);
					} else {
						((Player) event.getEntity()).setHealth(Math.max(((Player) event.getEntity()).getHealth() - damage, 0));
						player.sendMessage("Would not have killed you! Dmg: " + damage + " / Health Set to: " + ((Player) event.getEntity()).getHealth());
						event.setDamage(0);
					}
				} else {
					event.setDamage(0);
				}
			}
		}
	}
}
