package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.player.PlayerInteractEvent;

public interface UsableItem {

    void onHit(PlayerInteractEvent event);
    void onUse(PlayerInteractEvent event);

}
