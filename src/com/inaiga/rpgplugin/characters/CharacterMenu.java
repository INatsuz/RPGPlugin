package com.inaiga.rpgplugin.characters;

import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CharacterMenu {

	private static final int[] MENU_ITEM_POSITIONS = {10, 12, 14, 16};

	public static Inventory menuInventory = Bukkit.createInventory(null, 9 * 4, "Character Selection");

	public static void openCharacterMenu(Player player) {
		menuInventory.clear();

		RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(player);
		Character[] playerCharacters = rpgPlayer.getCharacters();

		for (int i = 0; i < playerCharacters.length; i++) {
			if (playerCharacters[i] != null){
				ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setDisplayName(playerCharacters[i].getCharacterClass() + " - LVL " + playerCharacters[i].getLevel());
				item.setItemMeta(itemMeta);

				menuInventory.setItem(MENU_ITEM_POSITIONS[i], item);
			} else {
				ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setDisplayName("Create Character");
				item.setItemMeta(itemMeta);

				menuInventory.setItem(MENU_ITEM_POSITIONS[i], item);
			}

		}

		player.openInventory(menuInventory);
	}

}
