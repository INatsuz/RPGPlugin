package com.inaiga.rpgplugin.customitems;

import org.bukkit.entity.Player;

public interface CustomItem {

    public void onHit();
    public void onUse(Player player);
}
