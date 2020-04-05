package com.inaiga.rpgplugin.customitems;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItems {
	STARTER_WAND_ITEM("Starter Wand", Material.BAMBOO, new StarterWand()),
	INTERMEDIATE_WAND_ITEM("Intermediate Wand", Material.DEBUG_STICK, new IntermediateWand()),
	CHARACTER_MENU_ITEM("Character Selection Menu", Material.COMPASS, new CharacterMenuItem()),
	STARTER_CHAIN_CHESTPLATE("Starter Chain Chestplate", Material.CHAINMAIL_CHESTPLATE, new StarterChainChestplate());

	private final String name;
	private final Material material;
	private final CustomItem classInstance;

	CustomItems(String name, Material material, CustomItem classInstance) {
		this.name = name;
		this.material = material;
		this.classInstance = classInstance;
	}

	public static ItemStack buildCustomItem(CustomItems item) {
		ItemStack itemStack = new ItemStack(item.getMaterial(), 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setUnbreakable(true);
		itemMeta.setDisplayName(item.getName());
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	public void onHit(PlayerInteractEvent event) {
		if (classInstance instanceof UsableItem) {
			((UsableItem)this.classInstance).onHit(event);
		}
	}

	public void onUse(PlayerInteractEvent event) {
		if (classInstance instanceof UsableItem) {
			((UsableItem)this.classInstance).onUse(event);
		}
	}

	public void onHitTaken(EntityDamageByEntityEvent event) {
		if (classInstance instanceof WearableItem) {
			((WearableItem)this.classInstance).onHitTaken(event);
		}
	}

	public String getName() {
		return name;
	}

	public Material getMaterial() {
		return material;
	}

	public CustomItem getClassInstance() {
		return classInstance;
	}

}
