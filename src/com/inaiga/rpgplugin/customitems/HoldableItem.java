package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.player.PlayerInteractEvent;

public interface HoldableItem {
    void onHold(PlayerInteractEvent event);
    public int getArmorMultiplier();
}
