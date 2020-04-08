package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.skills.Skill;
import com.inaiga.rpgplugin.skills.Skills;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SkillTreeMenu extends Menu {

    private enum MenuState {
        SKILL_SELECTION;
    }

    private static final MenuType menuType = MenuType.SKILL_TREE_MENU;

    private static MenuState menuState = MenuState.SKILL_SELECTION;

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

                }
                break;
        }
    }
}
