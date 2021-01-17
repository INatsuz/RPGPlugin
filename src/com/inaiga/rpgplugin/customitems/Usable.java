package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.player.PlayerInteractEvent;

public interface Usable extends CustomItem {

    /**
     * The method to write what happens when a player hits while the item is being held in the main hand
     * @param event The {@link org.bukkit.event.player.PlayerInteractEvent} instance
     * */
    void onHit(PlayerInteractEvent event);

    /**
     * The method to write what happens when an item is used
     * @param event The {@link org.bukkit.event.player.PlayerInteractEvent} instance
     * */
    void onUse(PlayerInteractEvent event);

}
