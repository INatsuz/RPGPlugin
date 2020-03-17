package com.inaiga.rpgplugin.customitems;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public interface CustomItem {

    public void onHit(PlayerInteractEvent event);
    public void onUse(PlayerInteractEvent event);

}
