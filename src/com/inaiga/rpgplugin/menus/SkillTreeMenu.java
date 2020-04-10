package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.skills.Skills;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class SkillTreeMenu extends Menu {

	private enum MenuState {
		SKILL_SELECTION,
		SKILL_OPTIONS,
		SKILL_UPGRADE_CONFIRMATION,
		SKILL_UNLOCK_CONFIRMATION;
	}

	private static final int[][] SKILL_SLOTS = {{10, 11, 12, 13, 14, 15, 16}, {28, 29, 30, 31, 32, 33, 34}};
	private static final int FIRST_SKILL_SLOT = 18;
	private static final Material SKILL_UNLOCKED = Material.GRAY_STAINED_GLASS_PANE;
	private static final Material SKILL_LOCKED = Material.LIME_STAINED_GLASS_PANE;

	private static final int UPGRADE_SKILL_SLOT = 20;
	private static final Material UPGRADE_SKILL_ITEM = Material.LIME_STAINED_GLASS_PANE;
	private static final int UNLOCK_SKILL_SLOT = 20;
	private static final Material UNLOCK_SKILL_ITEM = Material.LIME_STAINED_GLASS_PANE;

	private static final int UPGRADE_YES_SLOT = 20;
	private static final Material SKILL_UPGRADE_YES = Material.LIME_STAINED_GLASS_PANE;
	private static final int UPGRADE_NO_SLOT = 24;
	private static final Material SKILL_UPGRADE_NO = Material.RED_STAINED_GLASS_PANE;

	private static final int UNLOCK_YES_SLOT = 20;
	private static final Material SKILL_UNLOCK_YES = Material.LIME_STAINED_GLASS_PANE;
	private static final int UNLOCK_NO_SLOT = 24;
	private static final Material SKILL_UNLOCK_NO = Material.RED_STAINED_GLASS_PANE;

	private static final int BACK_SLOT = 24;
	private static final Material BACK_ITEM = Material.BARRIER;
	private static final int SKILL_INFO_SLOT = 22;
	private static final Material SKILL_INFO = Material.NETHER_STAR;

	private static final MenuType menuType = MenuType.SKILL_TREE_MENU;

	private MenuState menuState = MenuState.SKILL_SELECTION;

	private Skills firstSkill = null;

	private Skills chosenSkill = null;

	/**
	 * Constructor for the SkillTreeMenu
	 */
	SkillTreeMenu() {
		super(menuType);
	}

	@Override
	public void handleClick(InventoryClickEvent event) {
		switch (menuState) {
			case SKILL_SELECTION:
				if (event.getSlot() == FIRST_SKILL_SLOT) {
					chosenSkill = firstSkill;
					menuState = MenuState.SKILL_OPTIONS;
					update();
					break;

				} else if (Arrays.asList(SKILL_SLOTS[0]).contains(event.getSlot())) {
					chosenSkill = firstSkill.getNextSkills().get(0);
					for (int i = 0; i < SKILL_SLOTS[0].length; i++) {
						if (event.getSlot() == SKILL_SLOTS[0][i]) {
							menuState = MenuState.SKILL_OPTIONS;
                            update();
							break;
						}
						chosenSkill = chosenSkill.getNextSkills().get(0);
					}
				} else if (Arrays.asList(SKILL_SLOTS[1]).contains(event.getSlot())) {
					chosenSkill = firstSkill.getNextSkills().get(1);
					for (int i = 0; i < SKILL_SLOTS[1].length; i++) {
						if (event.getSlot() == SKILL_SLOTS[1][i]) {
							menuState = MenuState.SKILL_OPTIONS;
                            update();
							break;
						}
						chosenSkill = chosenSkill.getNextSkills().get(0);
					}
				}

				break;
			case SKILL_OPTIONS:
                if (getRpgPlayer().getActiveRPGCharacter().getSkills().contains(chosenSkill)) {
                    if(event.getSlot() == UPGRADE_SKILL_SLOT) {
                        menuState = MenuState.SKILL_UPGRADE_CONFIRMATION;
                        update();
                        break;
                    } else if (event.getSlot() == BACK_SLOT){
                        menuState = MenuState.SKILL_SELECTION;
                        update();
                        break;
                    }
                } else {
                    if (event.getSlot() == UNLOCK_SKILL_SLOT) {
                        menuState = MenuState.SKILL_UNLOCK_CONFIRMATION;
                        update();
                        break;
                    } else if (event.getSlot() == BACK_SLOT) {
                        menuState = MenuState.SKILL_SELECTION;
                        update();
                        break;
                    }
                }
				break;
			case SKILL_UPGRADE_CONFIRMATION:
                    if (event.getSlot() == UPGRADE_YES_SLOT) {
                        //to do
                        getPlayer().closeInventory();
                        break;
                    } else if (event.getSlot() == UPGRADE_NO_SLOT) {
                        menuState = MenuState.SKILL_OPTIONS;
                        update();
                        break;
                    }
				break;
			case SKILL_UNLOCK_CONFIRMATION:
                if (event.getSlot() == UNLOCK_YES_SLOT) {
                    //to do
                    getPlayer().closeInventory();
                    break;
                } else if (event.getSlot() == UNLOCK_NO_SLOT) {
                    menuState = MenuState.SKILL_OPTIONS;
                    update();
                    break;
                }
				break;
		}

	}

	@Override
	public void update() {
		getMenuInventory().clear(); //Clear the Inventory of the menu

		switch (menuState) {
			case SKILL_SELECTION:
				if (getRpgPlayer() != null) {
					RPGCharacter rpgCharacter = getRpgPlayer().getActiveRPGCharacter();  //Get the RPGPlayer Characters

					ArrayList<Skills> skills = rpgCharacter.getSkills();

					if (skills.isEmpty()) {
						for (Skills skill : Skills.values()) {
							if (skill.getRpgClass() == rpgCharacter.getCharacterRPGClass()) {
								firstSkill = skill;
								break;
							}
						}
					} else {
						firstSkill = skills.get(0);
					}

					if (firstSkill != null) {
						while (firstSkill.getRequiredSkill() != null) {
							firstSkill = firstSkill.getRequiredSkill();
						}
					} else {
						System.out.println("Error: Couldn't find skill");
					}

					ItemStack item = new ItemStack(skills.contains(firstSkill) ? SKILL_UNLOCKED : SKILL_LOCKED, 1);    //Creates CHARACTER_ITEM
					ItemMeta itemMeta = item.getItemMeta(); //Gets an ItemMeta

					if (itemMeta != null) {
						itemMeta.setDisplayName(firstSkill.name().replaceAll("_", " "));    //Changes the name of the ItemMeta
						item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
					}

					getMenuInventory().setItem(FIRST_SKILL_SLOT, item);   //Puts the item in the correspondent position

					for (int i = 0; i < firstSkill.getNextSkills().size(); i++) {
						Skills nextSkill = firstSkill.getNextSkills().get(i);

						for (int j = 0; true; j++) {
							item = new ItemStack(skills.contains(nextSkill) ? SKILL_UNLOCKED : SKILL_LOCKED, 1);    //Creates CHARACTER_ITEM
							itemMeta = item.getItemMeta(); //Gets an ItemMeta

							if (itemMeta != null) {
								itemMeta.setDisplayName(nextSkill.name().replaceAll("_", " "));    //Changes the name of the ItemMeta
								item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
							}

							getMenuInventory().setItem(SKILL_SLOTS[i][j], item);   //Puts the item in the correspondent position

							if (!nextSkill.getNextSkills().isEmpty()) {
								nextSkill = nextSkill.getNextSkills().get(0);
							} else {
								break;
							}
						}
					}
				}
				break;
			case SKILL_OPTIONS:
				if (getRpgPlayer() != null) {
					RPGCharacter rpgCharacter = getRpgPlayer().getActiveRPGCharacter();  //Get the RPGPlayer Characters

					if (rpgCharacter.getSkills().contains(chosenSkill)) {
						ItemStack item = new ItemStack(UPGRADE_SKILL_ITEM, 1);    //Creates CHARACTER_ITEM
						ItemMeta itemMeta = item.getItemMeta(); //Gets an ItemMeta

						if (itemMeta != null) {
							itemMeta.setDisplayName("UPGRADE: " + chosenSkill.name().replaceAll("_", " "));    //Changes the name of the ItemMeta
							item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
						}

						getMenuInventory().setItem(UPGRADE_SKILL_SLOT, item);   //Puts the item in the correspondent position

						item = new ItemStack(BACK_ITEM, 1);    //Creates CHARACTER_ITEM
						itemMeta = item.getItemMeta(); //Gets an ItemMeta

						if (itemMeta != null) {
							itemMeta.setDisplayName("Put me back!!");    //Changes the name of the ItemMeta
							item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
						}

						getMenuInventory().setItem(BACK_SLOT, item);   //Puts the item in the correspondent position

					} else {
						ItemStack item = new ItemStack(UNLOCK_SKILL_ITEM, 1);    //Creates CHARACTER_ITEM
						ItemMeta itemMeta = item.getItemMeta(); //Gets an ItemMeta

						if (itemMeta != null) {
							itemMeta.setDisplayName("UNLOCK: " + chosenSkill.name().replaceAll("_", " "));    //Changes the name of the ItemMeta
							item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
						}

						getMenuInventory().setItem(UNLOCK_SKILL_SLOT, item);   //Puts the item in the correspondent position

						item = new ItemStack(BACK_ITEM, 1);    //Creates CHARACTER_ITEM
						itemMeta = item.getItemMeta(); //Gets an ItemMeta

						if (itemMeta != null) {
							itemMeta.setDisplayName("Put me back!!");    //Changes the name of the ItemMeta
							item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
						}

						getMenuInventory().setItem(BACK_SLOT, item);   //Puts the item in the correspondent position
					}
				}
				break;
			case SKILL_UPGRADE_CONFIRMATION:
				//Creating CHARACTER_DELETE_YES
				ItemStack item = new ItemStack(SKILL_UPGRADE_YES, 1);  //Creates CHARACTER_DELETE_YES ItemStack
				ItemMeta itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("I said YES!!");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(UPGRADE_YES_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_INFO_ITEM
				item = new ItemStack(SKILL_INFO, 1); //Creates CHARACTER_INFO_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("UPGRADE: " + chosenSkill.name().replaceAll("_", " "));    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(SKILL_INFO_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_DELETE_NO
				item = new ItemStack(SKILL_UPGRADE_NO, 1); //Creates CHARACTER_DELETE_NO ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Actually no!");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(UPGRADE_NO_SLOT, item);   //Puts the item in the correspondent position
				break;
			case SKILL_UNLOCK_CONFIRMATION:
				//Creating CHARACTER_DELETE_YES
				item = new ItemStack(SKILL_UNLOCK_YES, 1);  //Creates CHARACTER_DELETE_YES ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("I said YES!!");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(UNLOCK_YES_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_INFO_ITEM
				item = new ItemStack(SKILL_INFO, 1); //Creates CHARACTER_INFO_ITEM ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("UNLOCK: " + chosenSkill.name().replaceAll("_", " "));    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(SKILL_INFO_SLOT, item);   //Puts the item in the correspondent position

				//Creating CHARACTER_DELETE_NO
				item = new ItemStack(SKILL_UNLOCK_NO, 1); //Creates CHARACTER_DELETE_NO ItemStack
				itemMeta = item.getItemMeta(); //Gets ItemMeta

				if (itemMeta != null) {
					itemMeta.setDisplayName("Actually no!");    //Changes the name of the ItemMeta
					item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
				}
				getMenuInventory().setItem(UNLOCK_NO_SLOT, item);   //Puts the item in the correspondent position
				break;
		}
	}
}
