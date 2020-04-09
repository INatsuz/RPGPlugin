package com.inaiga.rpgplugin.menus;


import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuManager {

	private static HashMap<Player, Menu> openMenus = new HashMap<Player, Menu>();

	/**
	 * Opens the given menu for the given Player
	 *
	 * @param player   {@link org.bukkit.entity.Player} to open the menu for
	 * @param menuType {@link com.inaiga.rpgplugin.menus.MenuType} of the menu
	 */
	public static boolean openMenuForPlayer(Player player, MenuType menuType) {
		switch (menuType) {
			case CHARACTER_SELECTION_MENU:
				CharacterMenu characterMenu = new CharacterMenu();
				characterMenu.openForPlayer(player);  //Opens this menu
				openMenus.put(player, characterMenu); //Stores the Menu the player is using

				openMenus.forEach((key, value) -> System.out.println(key + " : " + value));
				return true;
			case SKILL_TREE_MENU:
				SkillTreeMenu skillMenu = new SkillTreeMenu();
				skillMenu.openForPlayer(player);  //Opens this menu
				openMenus.put(player, skillMenu); //Stores the Menu the player is using

				openMenus.forEach((key, value) -> System.out.println(key + " : " + value));
				return true;
			default:
				return false;
		}
	}

	public static HashMap<Player, Menu> getOpenMenus() {
		return openMenus;
	}

	public static void setOpenMenus(HashMap<Player, Menu> openMenus) {
		MenuManager.openMenus = openMenus;
	}

	public static boolean closeOpenMenuForPlayer(Player player) {
		openMenus.remove(player);
		openMenus.forEach((key, value) -> System.out.println(key + " : " + value));

		return true;
	}

}