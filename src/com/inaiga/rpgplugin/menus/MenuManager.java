package com.inaiga.rpgplugin.menus;

import org.bukkit.entity.Player;

public class MenuManager {

    public static boolean openMenuForPlayer(Player player, MenuType menuType){
        switch (menuType) {
            case CHARACTER_SELECTION_MENU:
                new CharacterMenu().openForPlayer(player);

                return true;
            default:
                return false;
        }
    }
}
