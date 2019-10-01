package com.inaiga.rpgplugin.menus;

public class MenuManager {

    public static Menu createMenu(MenuType menuType){
        switch (menuType) {
            case CHARACTER_SELECTION_MENU:
                return new CharacterMenu();
            default:
                return null;
        }
    }

    public enum MenuType {
        CHARACTER_SELECTION_MENU
    }

}
