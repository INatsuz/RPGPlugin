package com.inaiga.rpgplugin.customitems;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItems {
	STARTER_WAND_ITEM("Starter Wand", Material.BAMBOO, new StarterWand()),
	INTERMEDIATE_WAND_ITEM("Intermediate Wand", Material.DEBUG_STICK, new IntermediateWand()),
	CHARACTER_MENU_ITEM("Menu", Material.COMPASS, new CharacterMenuItem());

	private final String name;
	private final Material material;
	private final UsableItem classInstance;

	CustomItems(String name, Material material, UsableItem classInstance) {
		this.name = name;
		this.material = material;
		this.classInstance = classInstance;
	}

	public static ItemStack buildCustomItem(CustomItems item) {
		ItemStack itemStack = new ItemStack(item.getMaterial(), 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(item.getName());
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	public void onHit(PlayerInteractEvent event){
		this.classInstance.onHit(event);
	}

	public void onUse(PlayerInteractEvent event){
		this.classInstance.onUse(event);
	}

	public String getName() {
		return name;
	}

	public Material getMaterial() {
		return material;
	}

}
