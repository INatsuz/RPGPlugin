package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.menus.MenuManager;
import com.inaiga.rpgplugin.menus.MenuType;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public enum CustomItems implements CustomItem {
	STARTER_STAFF_ITEM("Starter Staff", Material.BAMBOO) {
		@Override
		public void onHit(PlayerInteractEvent event) {
			RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());
			if (rpgPlayer != null) {
				if (rpgPlayer.getActiveRPGCharacter() != null) {
					switch (rpgPlayer.getActiveRPGCharacter().getCharacterClass()) {
						case WIZARD:
							event.getPlayer().damage(50);
							break;
						case ROGUE:
							event.getPlayer().damage(3);
							break;
						default:
							event.getPlayer().damage(9);
							break;
					}
				}
			}
		}

		@Override
		public void onUse(PlayerInteractEvent event) {
			RPGPlayer Rpgplayer = PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());

			Vector direction = event.getPlayer().getEyeLocation().getDirection().clone().normalize();

			Bat entity = (Bat) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation().add(0, 200, 0), EntityType.BAT);
			entity.setAI(false);
			entity.setSilent(true);
			entity.setInvulnerable(true);
			entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 1, false, false));
			for (int i = 0; i < 40; i++) {
				Vector newVector = direction.clone().multiply(i);
				Location currentSpellLocation = event.getPlayer().getEyeLocation().add(newVector);
				event.getPlayer().getWorld().spawnParticle(Particle.CRIT, currentSpellLocation, 1);

				entity.teleport(currentSpellLocation);
				List<Entity> entityList = entity.getNearbyEntities(1, 1, 1);

				entityList.forEach(entityItem -> {
					if (entityItem instanceof Damageable && entityItem != event.getPlayer()) {
						((Damageable) entityItem).damage(50);
					}
				});

				if (event.getPlayer().getWorld().getBlockAt(currentSpellLocation).getBlockData().getMaterial().isSolid()){
					break;
				}
			}
			entity.remove();
		}
	},
	CHARACTER_MENU_ITEM("Menu", Material.COMPASS) {
		@Override
		public void onHit(PlayerInteractEvent event) {

		}

		@Override
		public void onUse(PlayerInteractEvent event) {
			MenuManager.openMenuForPlayer(event.getPlayer(), MenuType.CHARACTER_SELECTION_MENU);
			event.getPlayer().sendMessage("Opening Character Menu");
		}
	};

	private final String name;
	private final Material material;

	CustomItems(String name, Material material) {
		this.name = name;
		this.material = material;
	}

	public static ItemStack buildCustomItem(CustomItems item) {
		ItemStack itemStack = new ItemStack(item.getMaterial(), 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(item.getName());
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	public String getName() {
		return name;
	}

	public Material getMaterial() {
		return material;
	}

}
