package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.player.PlayerInteractEvent;

public interface Usable extends CustomItem {

    void onHit(PlayerInteractEvent event);
    void onUse(PlayerInteractEvent event);

}
