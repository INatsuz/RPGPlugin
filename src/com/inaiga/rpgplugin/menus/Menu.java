package com.inaiga.rpgplugin.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class Menu {

    private MenuType menuType = null;

    private Inventory menuInventory = null;
    private String name;
    private int slots;

    /**
     * Constructor for the CharacterMenu class
     * @param name The name of the Menu as a String
     * @param slots Number of slots in the Menu as an int
     * */
    Menu(MenuType menuType) {
        this.menuType = menuType;
        this.name = menuType.getName();
        this.slots = menuType.getSlots();

        menuInventory = Bukkit.createInventory(null, slots, name);  //Create an inventory
    }

    /**
     * Returns the menu's inventory
     * @return The menu's {@link org.bukkit.inventory.Inventory}
     * */
    public Inventory getMenuInventory() {
        return menuInventory;
    }

    /**
     * Sets the menu's inventory
     * @param menuInventory {@link org.bukkit.inventory.Inventory} instance
     * */
    public void setMenuInventory(Inventory menuInventory) {
        this.menuInventory = menuInventory;
    }

    /**
     * Returns the menu's name
     * @return The menu's name as a String
     * */
    public String getName() {
        return name;
    }

    /**
     * Sets the menu's name
     * @param name The menu's name as a String
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of menu's slots
     * @return The number of menu's slots as an int
     * */
    public int getSlots() {
        return slots;
    }

    /**
     * Sets the number of menu's slots
     * @param slots The number of the menu's slots as an int
     * */
    public void setSlots(int slots) {
        this.slots = slots;
    }

    //Opens the Menu for the given Player
    public abstract void openForPlayer(Player player);

    //Handles click events for the inventory
    public abstract void handleClick(InventoryClickEvent event);

}
