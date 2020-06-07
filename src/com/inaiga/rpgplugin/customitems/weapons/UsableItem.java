package com.inaiga.rpgplugin.customitems.weapons;

import com.inaiga.rpgplugin.customitems.CustomItem;
import org.bukkit.event.player.PlayerInteractEvent;

public interface UsableItem extends CustomItem {

    void onHit(PlayerInteractEvent event);
    void onUse(PlayerInteractEvent event);

}
