package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CharacterMenu extends Menu {

	private static final int[] MENU_CHARACTER_SLOTS = {10, 12, 14, 16};
	private static final Material CHARACTER_ITEM = Material.BLACK_STAINED_GLASS_PANE;
	private static final Material CHARACTER_CREATE_ITEM = Material.LIME_STAINED_GLASS_PANE;

	private int menuState = 0; // 0 → Initial Screen, 1 → Character Options

	private Player player = null;
	private RPGPlayer rpgPlayer = null;

	private RPGCharacter chosenCharacter = null;

	/**
	 * Constructor for the CharacterMenu class
	 */
	CharacterMenu() {
		super(MenuType.CHARACTER_SELECTION_MENU.getName(), MenuType.CHARACTER_SELECTION_MENU.getSlots());
	}

	/**
	 * Opens the CharacterMenu for the given Player
	 *
	 * @param player {@link org.bukkit.entity.Player}
	 */
	@Override
	public void openForPlayer(Player player) {
		this.player = player;
		rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(player);
		update();

		player.openInventory(getMenuInventory());   //Opens the Menu
	}

	@Override
	public void handleClick(InventoryClickEvent event) {
		if (menuState == 0) {
			for (int i = 0; i < MENU_CHARACTER_SLOTS.length; i++) {
				if (event.getSlot() == MENU_CHARACTER_SLOTS[i]) {
					if (rpgPlayer != null) {
						chosenCharacter = rpgPlayer.getRPGCharacters()[i];

						menuState = 1;
						update();
					}
				}
			}
		}
		System.out.println("Chill out, I'm handling it");
	}

	private void update() {
		getMenuInventory().clear(); //Clear the Inventory of the menu

		switch (menuState) {
			case 0:
				if (rpgPlayer != null) {
					RPGCharacter[] playerRPGCharacters = rpgPlayer.getRPGCharacters();  //Get the RPGPlayer Characters

					for (int i = 0; i < playerRPGCharacters.length; i++) {
						if (playerRPGCharacters[i] != null) {
							ItemStack item = new ItemStack(CHARACTER_ITEM, 1);    //Creates an item
							ItemMeta itemMeta = item.getItemMeta(); //Creates an ItemMeta

							if (itemMeta != null) {
								itemMeta.setDisplayName(playerRPGCharacters[i].getCharacterClass() + " - LVL " + playerRPGCharacters[i].getLevel());    //Changes the name of the ItemMeta
								item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
							}

							getMenuInventory().setItem(MENU_CHARACTER_SLOTS[i], item);   //Puts the item in the correspondent position
						} else {
							ItemStack item = new ItemStack(CHARACTER_CREATE_ITEM, 1);    //Creates an item
							ItemMeta itemMeta = item.getItemMeta(); //Creates an ItemMeta

							if (itemMeta != null) {
								itemMeta.setDisplayName("Create Character");    //Changes the name of the item Meta
								item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
							}

							getMenuInventory().setItem(MENU_CHARACTER_SLOTS[i], item);   //Puts the item in the correspondent position
						}

					}
				}
				break;
			case 1:
				Inventory newInventory = Bukkit.createInventory(null, MenuType.CHARACTER_SELECTION_MENU.getSlots(), "Teste");
				setMenuInventory(newInventory);
				player.closeInventory();
				player.openInventory(getMenuInventory());
			default:
				System.out.println("Nothing");
		}
	}

}
