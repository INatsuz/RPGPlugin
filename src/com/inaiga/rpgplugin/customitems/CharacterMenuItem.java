package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.menus.MenuManager;
import com.inaiga.rpgplugin.menus.MenuType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CharacterMenuItem extends ItemStack implements CustomItem{

    public CharacterMenuItem(@NotNull Material type, int amount) {
        super(type, amount);
    }

    @Override
    public void onHit() {

    }

    @Override
    public void onUse(Player player) {
        MenuManager.openMenuForPlayer(player, MenuType.CHARACTER_SELECTION_MENU);
    }

}