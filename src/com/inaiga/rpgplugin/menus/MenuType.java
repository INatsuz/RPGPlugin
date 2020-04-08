package com.inaiga.rpgplugin.menus;

public enum MenuType {
	CHARACTER_SELECTION_MENU("CHARACTER SELECTION", 36),
	SKILL_TREE_MENU("SKILLS", 63);

	private final String name;
	private final int slots;

	/**
	 * Constructor of the menu
	 * @param name Name of the menu as a String
	 * @param slots Number of slots as an int
	 * */
	private MenuType(String name, int slots){
		this.name = name;
		this.slots = slots;
	}

	/**
	 * Returns the name of the menu
	 * @return The name of the menu as a String
	 * */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of slots
	 * @return The number of slots as an int
	 * */
	public int getSlots() {
		return slots;
	}

}
