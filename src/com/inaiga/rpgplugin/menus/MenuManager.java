package com.inaiga.rpgplugin.menus;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class MenuManager {

    private static ArrayList<Menu> openMenus = new ArrayList<>();

    /**
     * Opens the given menu for the given Player
     * @param player {@link org.bukkit.entity.Player} to open the menu for
     * @param menuType {@link com.inaiga.rpgplugin.menus.MenuType} of the menu
     * */
    public static boolean openMenuForPlayer(Player player, MenuType menuType){
        switch (menuType) {
            case CHARACTER_SELECTION_MENU:
                CharacterMenu characterMenu = new CharacterMenu();
                characterMenu.openForPlayer(player);  //Opens this menu
                openMenus.add(characterMenu);

                return true;
            default:
                return false;
        }
    }

    public static ArrayList<Menu> getOpenMenus() {
        return openMenus;
    }

    public static void setOpenMenus(ArrayList<Menu> openMenus) {
        MenuManager.openMenus = openMenus;
    }

}