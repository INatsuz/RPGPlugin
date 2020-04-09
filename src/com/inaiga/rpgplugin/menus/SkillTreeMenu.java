package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.skills.Skills;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SkillTreeMenu extends Menu {

    private enum MenuState {
        SKILL_SELECTION;
    }

    private static final int[][] SKILL_SLOTS = {{10, 11, 12, 13, 14, 15, 16}, {28, 29, 30, 31, 32, 33, 34}};
    private static final int FIRST_SKILL_SLOT = 18;
    private static final Material SKILL_UNLOCKED = Material.GRAY_STAINED_GLASS_PANE;
    private static final Material SKILL_LOCKED = Material.LIME_STAINED_GLASS_PANE;

    private static final MenuType menuType = MenuType.SKILL_TREE_MENU;

    private MenuState menuState = MenuState.SKILL_SELECTION;

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
                    Skills firstSkill = null;

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
                        itemMeta.setDisplayName(firstSkill.name().replaceAll("_",  " "));    //Changes the name of the ItemMeta
                        item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
                    }

                    getMenuInventory().setItem(FIRST_SKILL_SLOT, item);   //Puts the item in the correspondent position

                    for (int i = 0; i < firstSkill.getNextSkills().size(); i++) {
                        Skills nextSkill = firstSkill.getNextSkills().get(i);

                        for (int j = 0; true; j++) {
                            item = new ItemStack(skills.contains(nextSkill) ? SKILL_UNLOCKED : SKILL_LOCKED, 1);    //Creates CHARACTER_ITEM
                            itemMeta = item.getItemMeta(); //Gets an ItemMeta

                            if (itemMeta != null) {
                                itemMeta.setDisplayName(nextSkill.name().replaceAll("_",  " "));    //Changes the name of the ItemMeta
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
        }
    }
}
