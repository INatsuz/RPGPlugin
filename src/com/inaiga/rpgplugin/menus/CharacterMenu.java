package com.inaiga.rpgplugin.menus;

import com.inaiga.rpgplugin.characters.Character;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CharacterMenu extends Menu {

    public static final String MENU_NAME = "CHARACTER SELECTION";
    private static final int MENU_SLOTS = 36;
    private static final int[] MENU_ITEM_POSITIONS = {10, 12, 14, 16};

    public CharacterMenu() {
        super(MENU_SLOTS, MENU_NAME);
    }

    @Override
    public void openForPlayer(Player player) {
        getMenuInventory().clear();

        RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer(player);

        if (rpgPlayer != null) {
            Character[] playerCharacters = rpgPlayer.getCharacters();

            for (int i = 0; i < playerCharacters.length; i++) {
                if (playerCharacters[i] != null) {
                    ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
                    ItemMeta itemMeta = item.getItemMeta();

                    if (itemMeta != null) {
                        itemMeta.setDisplayName(playerCharacters[i].getCharacterClass() + " - LVL " + playerCharacters[i].getLevel());
                        item.setItemMeta(itemMeta);
                    }

                    getMenuInventory().setItem(MENU_ITEM_POSITIONS[i], item);
                } else {
                    ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
                    ItemMeta itemMeta = item.getItemMeta();

                    if (itemMeta != null) {
                        itemMeta.setDisplayName("Create Character");
                        item.setItemMeta(itemMeta);
                    }

                    getMenuInventory().setItem(MENU_ITEM_POSITIONS[i], item);
                }

            }
        }

        player.openInventory(getMenuInventory());
    }
}
