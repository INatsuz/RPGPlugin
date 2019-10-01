package com.inaiga.rpgplugin.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class Menu {

    private Inventory menuInventory = null;
    private String name;
    private int slots;

    Menu(int slots, String name) {
        this.name = name;
        this.slots = slots;

        menuInventory = Bukkit.createInventory(null, slots, name);
    }

    public Inventory getMenuInventory() {
        return menuInventory;
    }

    public void setMenuInventory(Inventory menuInventory) {
        this.menuInventory = menuInventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    abstract void openMenu(Player player);

}
