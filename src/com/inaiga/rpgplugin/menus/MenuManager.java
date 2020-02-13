package com.inaiga.rpgplugin.menus;

import org.bukkit.entity.Player;

public class MenuManager {

    /**
     * Opens the given menu for the given Player
     * @param player {@link org.bukkit.entity.Player} to open the menu for
     * @param menuType {@link com.inaiga.rpgplugin.menus.MenuType} of the menu
     * */
    public static boolean openMenuForPlayer(Player player, MenuType menuType){
        switch (menuType) {
            case CHARACTER_SELECTION_MENU:
                new CharacterMenu().openForPlayer(player);  //Opens this menu

                return true;
            default:
                return false;
        }
    }

}