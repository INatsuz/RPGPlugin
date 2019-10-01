package com.inaiga.rpgplugin.menus;

public enum MenuType {
	CHARACTER_SELECTION_MENU("CHARACTER SELECTION", 36);

	private final String name;
	private final int slots;

	private MenuType(String name, int slots){
		this.name = name;
		this.slots = slots;
	}

	public String getName() {
		return name;
	}

	public int getSlots() {
		return slots;
	}

	@Override
	public String toString() {
		return name;
	}

}
