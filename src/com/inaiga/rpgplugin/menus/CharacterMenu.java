package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CharacterMenu extends Menu {

    private static final int[] MENU_ITEM_POSITIONS = {10, 12, 14, 16};

    /**
     * Constructor for the CharacterMenu class
     * */
    CharacterMenu() {
        super(MenuType.CHARACTER_SELECTION_MENU.getName(), MenuType.CHARACTER_SELECTION_MENU.getSlots());
    }

    /**
     * Opens the CharacterMenu for the given Player
     * @param player {@link org.bukkit.entity.Player}
     * */
    @Override
    public void openForPlayer(Player player) {
        getMenuInventory().clear(); //Clear the Inventory of the menu

        RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(player); //Get the RPGPlayer

        if (rpgPlayer != null) {
            RPGCharacter[] playerRPGCharacters = rpgPlayer.getRPGCharacters();  //Get the RPGPlayer Characters

            for (int i = 0; i < playerRPGCharacters.length; i++) {
                if (playerRPGCharacters[i] != null) {
                    ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);    //Creates an item
                    ItemMeta itemMeta = item.getItemMeta(); //Creates an ItemMeta

                    if (itemMeta != null) {
                        itemMeta.setDisplayName(playerRPGCharacters[i].getCharacterClass() + " - LVL " + playerRPGCharacters[i].getLevel());    //Changes the name of the ItemMeta
                        item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
                    }

                    getMenuInventory().setItem(MENU_ITEM_POSITIONS[i], item);   //Puts the item in the correspondent position
                } else {
                    ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);    //Creates an item
                    ItemMeta itemMeta = item.getItemMeta(); //Creates an ItemMeta

                    if (itemMeta != null) {
                        itemMeta.setDisplayName("Create Character");    //Changes the name of the item Meta
                        item.setItemMeta(itemMeta); //Sets the correspondent ItemMeta to the item
                    }

                    getMenuInventory().setItem(MENU_ITEM_POSITIONS[i], item);   //Puts the item in the correspondent position
                }

            }
        }

        player.openInventory(getMenuInventory());   //Opens the Menu
    }
}