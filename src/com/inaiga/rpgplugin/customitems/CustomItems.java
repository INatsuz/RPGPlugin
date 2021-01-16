package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.customitems.armors.guardian.StarterGuardianChestplate;
import com.inaiga.rpgplugin.customitems.armors.ranger.StarterRangerChestplate;
import com.inaiga.rpgplugin.customitems.armors.rogue.StarterRogueChestplate;
import com.inaiga.rpgplugin.customitems.armors.Wearable;
import com.inaiga.rpgplugin.customitems.armors.wizard.StarterWizardChestplate;
import com.inaiga.rpgplugin.customitems.armors.misc.StarterChainChestplate;
import com.inaiga.rpgplugin.customitems.armors.misc.UselessSunCap;
import com.inaiga.rpgplugin.customitems.utility.CharacterMenuItem;
import com.inaiga.rpgplugin.customitems.weapons.wizard.IntermediateWand;
import com.inaiga.rpgplugin.customitems.weapons.wizard.StarterWand;
import java.util.HashMap;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public enum CustomItems implements Usable, Strikeable, Wearable {
	STARTER_WAND_ITEM("Starter Wand", Material.BAMBOO, new StarterWand()),
	INTERMEDIATE_WAND_ITEM("Intermediate Wand", Material.DEBUG_STICK, new IntermediateWand()),
	CHARACTER_MENU_ITEM("Character Selection Menu", Material.COMPASS, new CharacterMenuItem()),
	STARTER_CHAIN_CHESTPLATE("Starter Chain Chestplate", Material.CHAINMAIL_CHESTPLATE, new StarterChainChestplate()),
	STARTER_ROGUE_CHESTPLATE("Starter Rogue Chestplate", Material.IRON_CHESTPLATE, new StarterRogueChestplate()),
	STARTER_WIZARD_CHESTPLATE("Starter Wizard Chestplate", Material.GOLDEN_CHESTPLATE, new StarterWizardChestplate()),
	STARTER_GUARDIAN_CHESTPLATE("Starter Guardian Chestplate", Material.IRON_CHESTPLATE, new StarterGuardianChestplate()),
	STARTER_RANGER_CHESTPLATE("Starter Ranger Chestplate", Material.LEATHER_CHESTPLATE, new StarterRangerChestplate()),
	USELESS_SUN_CAP("Useless Sun Cap", Material.LEATHER_HELMET, Color.BLACK, new UselessSunCap());

	private static final HashMap<String, CustomItem> CUSTOM_ITEMS_HASHMAP = new HashMap<>();

	private final String name;
	private final Material material;
	private final CustomItem classInstance;
	private final Color color;

	CustomItems(String name, Material material, CustomItem classInstance) {
		CustomItemManager.addCustomItem(this);

		this.name = name;
		this.material = material;
		this.classInstance = classInstance;

		color = null;
	}

	CustomItems(String name, Material material, Color color, CustomItem classInstance) {
		this.name = name;
		this.material = material;
		this.classInstance = classInstance;

		if (new ItemStack(this.material).getItemMeta() instanceof LeatherArmorMeta) {
			this.color = color;
		} else {
			this.color = null;
		}

		CustomItemManager.addCustomItem(this);
	}

	public static ItemStack buildCustomItem(CustomItems item) {
		ItemStack itemStack = new ItemStack(item.getMaterial(), 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setUnbreakable(true);
		itemMeta.setDisplayName(item.getName());
		itemStack.setItemMeta(itemMeta);
		if (item.getColor() != null && itemMeta instanceof LeatherArmorMeta) {
			((LeatherArmorMeta) itemMeta).setColor(item.getColor());
		}

		return itemStack;
	}

	public void onHit(PlayerInteractEvent event) {
		if (classInstance instanceof Usable) {
			((Usable) this.classInstance).onHit(event);
		}
	}

	public void onUse(PlayerInteractEvent event) {
		if (classInstance instanceof Usable) {
			((Usable) this.classInstance).onUse(event);
		}
	}

	public void onHitTaken(EntityDamageByEntityEvent event) {
		if (classInstance instanceof Wearable) {
			((Wearable) this.classInstance).onHitTaken(event);
		}
	}

	@Override
	public void onStrike(EntityDamageByEntityEvent event) {
		if (classInstance instanceof Strikeable) {
			((Strikeable) this.classInstance).onStrike(event);
		}
	}

	public String getName() {
		return name;
	}

	public Material getMaterial() {
		return material;
	}

	public Color getColor() {
		return color;
	}

	public CustomItem getClassInstance() {
		return classInstance;
	}

	public static CustomItem getCustomItemByName(String name){
		return CUSTOM_ITEMS_HASHMAP.get(name);
	}
}
