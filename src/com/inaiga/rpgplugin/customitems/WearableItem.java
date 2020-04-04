package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface WearableItem extends CustomItem {

    void onHitTaken(EntityDamageByEntityEvent event);

}
