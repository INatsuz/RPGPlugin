package com.inaiga.rpgplugin.customitems;

import java.util.HashMap;

public class CustomItemManager {

	private static HashMap<String, CustomItems> customItems = new HashMap<>();

	static {
		for (CustomItems item : CustomItems.values()) {
			System.out.println(item.getName());
			addCustomItem(item);
		}
	}

	public static void addCustomItem(CustomItems customItem){
		System.out.println("Custom Item Added");

		customItems.put(customItem.getName(), customItem);
	}

	public static CustomItems getCustomItemByName(String name) {
		System.out.println("Size of the hashmap: " + customItems.size());

		return customItems.get(name);
	}

}
