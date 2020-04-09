package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.classes.RPGClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CharacterMenu extends Menu {

	private enum MenuState {
		CHARACTER_SELECTION,
		CHARACTER_OPTIONS,
		CHARACTER_DELETE_CONFIRMATION,
		CHARACTER_CREATION
	}

	private static final int BACK_SLOT = 31;
	private static final Material BACK_ITEM = Material.BARRIER;
	private static final int CHARACTER_INFO_SLOT = 13;
	private static final Material CHARACTER_INFO_ITEM = Material.NETHER_STAR;

	private static final int[] MENU_CHARACTER_SLOTS = {10, 12, 14, 16};
	private static final Material CHARACTER_ITEM = Material.BLACK_STAINED_GLASS_PANE;
	private static final Material CHARACTER_CREATE_ITEM = Material.LIME_STAINED_GLASS_PANE;

	private static final int PLAY_SLOT = 11;
	private static final Material CHARACTER_CHOOSE_ITEM = Material.LIME_STAINED_GLASS_PANE;
	private static final int DELETE_SLOT = 15;
	private static final Material CHARACTER_DELETE_ITEM = Material.RED_STAINED_GLASS_PANE;

	private static final int DELETE_YES_SLOT = 11;
	private static final Material CHARACTER_DELETE_YES = Material.LIME_STAINED_GLASS_PANE;
	private static final int DELETE_NO_SLOT = 15;
	private static final Material CHARACTER_DELETE_NO = Material.RED_STAINED_GLASS_PANE;

	private static final int GUARDIAN_CLASS_SLOT = 10;
	private static final Material GUARDIAN_CLASS_ITEM = Material.IRON_CHESTPLATE;
	private static final int RANGER_CLASS_SLOT = 12;
	private static final Material RANGER_CLASS_ITEM = Material.BOW;
	private static final int ROGUE_CLASS_SLOT = 14;
	private static final Material ROGUE_CLASS_ITEM = Material.LEATHER_BOOTS;
	private static final int WIZARD_CLASS_SLOT = 16;
	private static final Material WIZARD_CLASS_ITEM = Material.DEBUG_STICK;

	private static final MenuType menuType = MenuType.CHARACTER_SELECTION_MENU;

	private MenuState menuState = MenuState.CHARACTER_SELECTION;

	private RPGCharacter chosenCharacter = null;

	/**
	 * Constructor for the CharacterMenu class
	 */
	CharacterMenu() {
		super(menuType);
	}

	@Override
	public void handleClick(InventoryClickEvent event) {
		switch (menuState) {
			case CHARACTER_SELECTION:
				for (int i = 0; i < MENU_CHARACTER_SLOTS.length; i++) {
					if (event.getSlot() == MENU_CHARACTER_SLOTS[i]) {
						if (getRpgPlayer() != null) {
							chosenCharacter = getRpgPlayer().getRpgCharacters()[i];
							if (chosenCharacter != null) {
								menuState = MenuState.CHARACTER_OPTIONS;
							} else {
								menuState = MenuState.CHARACTER_CREATION;
							}
							update();
						}
					}
				}
				break;
			case CHARACTER_OPTIONS:
				if (event.getSlot() == PLAY_SLOT) {
					if (getRpgPlayer() != null) {
						getRpgPlayer().chooseCharacter(chosenCharacter);
						getPlayer().closeInventory();
					}
				} else if (event.getSlot() == DELETE_SLOT) {
					if (getRpgPlayer() != null) {
						menuState = MenuState.CHARACTER_DELETE_CONFIRMATION;
						update();
					}
				} else if (event.getSlot() == BACK_SLOT) {
					if (getRpgPlayer() != null) {
						menuState = MenuState.CHARACTER_SELECTION;
						update();
					}
				}
				break;
			case CHARACTER_DELETE_CONFIRMATION:
				if (event.getSlot() == DELETE_YES_SLOT) {
					if (getRpgPlayer() != null) {
						getRpgPlayer().deleteCharacter(chosenCharacter);
						menuState = MenuState.CHARACTER_SELECTION;
						update();
					}
				} else if (event.getSlot() == DELETE_NO_SLOT) {
					if (getRpgPlayer() != null) {
						menuState = MenuState.CHARACTER_OPTIONS;
						update();
					}
				}
				break;
			case CHARACTER_CREATION:
				if (event.getSlot() == BACK_SLOT) {
					menuState = MenuState.CHARACTER_SELECTION;
					update();
				} else if (event.getCurrentItem() != null){
					getRpgPlayer().addCharacter(new RPGCharacter(RPGClass.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().toUpperCase()), 1));
					menuState = MenuState.CHARACTER_SELECTION;
					update();
				}
		}

		System.out.println("Chill out, I'm handling it");
	}

	@Override
	public void update() {
		getMenuInventory().clear(); //Clear the Inventory of the menu

		switch (menuState) {
			case CHARACTER_SELECTION:
				if (getRpgPlayer() != null) {
					RPGCharacter[] playerRPGCharacters = getRpgPlayer().getRpgCharacters();  //Get the RPGPlayer Characters

					for (int i = 0; i < playerRPGCharacters.length; i++) {
						if (playerRPGCharacters[i] != null) {
							ItemStack item = new ItemStack(CHARACTER_ITEM, 1);    //Creates CHARACTER_ITEM
							ItemMeta itemMeta = item.getItemMeta(); //Gets an ItemMeta

							if (itemMeta != null) {
								itemMeta.setDisplayName(playerRPGCharacters[i].getCharacterRPGClass() + " - LVL " + playerRPGCharacters[i].getLevel());    //Changes the name of the ItemMeta
								item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
							}

							getMenuInventory().setItem(MENU_CHARACTER_SLOTS[i], item);   //Puts the item in the correspondent position
						} else {
							ItemStack item = new ItemStack(CHARACTER_CREATE_ITEM, 1);    //Creates an item
							ItemMeta itemMeta = item.getItemMeta(); //Gets ItemMeta

							if (itemMeta != null) {
								itemMeta.setDisplayName("Create Character");    //Changes the name of the item Meta
								item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
							}

							getMenuInventory().setItem(MENU_CHARACTER_SLOTS[i], item);   //Puts the item in the correspondent position
						}

					}
				}
				break;
			case CHARACTER_OPTIONS:
				//Creating CHARACTER_CHOOSE_ITEM
				ItemStack item = new ItemStack(CHARACTER_CHOOSE_ITEM, 1);    //Creates CHARACTER_CHOOSE_ITEM ItemStack
				ItemMeta itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Play");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(PLAY_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_INFO_ITEM
				item = new ItemStack(CHARACTER_INFO_ITEM, 1); //Creates CHARACTER_INFO_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName(chosenCharacter.getCharacterRPGClass() + " - LVL " + chosenCharacter.getLevel());    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(CHARACTER_INFO_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_INFO_ITEM
				item = new ItemStack(CHARACTER_DELETE_ITEM, 1); //Creates CHARACTER_INFO_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Delete");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(DELETE_SLOT, item);   //Puts the item in the correspondent position

				//Creating BACK_ITEM
				item = new ItemStack(BACK_ITEM, 1); //Creates BACK_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Back");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(BACK_SLOT, item);   //Puts the item in the correspondent position
				break;
			case CHARACTER_DELETE_CONFIRMATION:
				//Creating CHARACTER_DELETE_YES
				item = new ItemStack(CHARACTER_DELETE_YES, 1);  //Creates CHARACTER_DELETE_YES ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("I said YES!!");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(DELETE_YES_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_INFO_ITEM
				item = new ItemStack(CHARACTER_INFO_ITEM, 1); //Creates CHARACTER_INFO_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName(chosenCharacter.getCharacterRPGClass() + " - LVL " + chosenCharacter.getLevel());    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(CHARACTER_INFO_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_DELETE_NO
				item = new ItemStack(CHARACTER_DELETE_NO, 1); //Creates CHARACTER_DELETE_NO ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Actually no!");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(DELETE_NO_SLOT, item);   //Puts the item in the correspondent position

				break;
			case CHARACTER_CREATION:
				//Creating GUARDIAN_CLASS_ITEM
				item = new ItemStack(GUARDIAN_CLASS_ITEM, 1);    //Creates GUARDIAN_CLASS_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Guardian");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(GUARDIAN_CLASS_SLOT, item);   //Puts the item in the correspondent position

				//Creating RANGER_CLASS_ITEM
				item = new ItemStack(RANGER_CLASS_ITEM, 1); //Creates RANGER_CLASS_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Ranger");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(RANGER_CLASS_SLOT, item);   //Puts the item in the correspondent position

				//Creating ROGUE_CLASS_ITEM
				item = new ItemStack(ROGUE_CLASS_ITEM, 1); //Creates ROGUE_CLASS_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Rogue");    //Changes the name of the ItemMeta
					((LeatherArmorMeta) itemMeta).setColor(Color.BLACK); //Changer color of item
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(ROGUE_CLASS_SLOT, item);   //Puts the item in the correspondent position

				//Creating WIZARD_CLASS_ITEM
				item = new ItemStack(WIZARD_CLASS_ITEM, 1); //Creates WIZARD_CLASS_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Wizard");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(WIZARD_CLASS_SLOT, item);   //Puts the item in the correspondent position

				//Creating BACK_ITEM
				item = new ItemStack(BACK_ITEM, 1); //Creates BACK_ITEM
				itemMeta = item.getItemMeta(); //Creates an ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Back");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(BACK_SLOT, item);   //Puts the item in the correspondent position

				break;
			default:
				System.out.println("Nothing");
		}
	}

}
