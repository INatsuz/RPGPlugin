package com.inaiga.rpgplugin.customitems.armors;

import com.inaiga.rpgplugin.customitems.CustomItem;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface WearableItem extends CustomItem {

    void onHitTaken(EntityDamageByEntityEvent event);

}
