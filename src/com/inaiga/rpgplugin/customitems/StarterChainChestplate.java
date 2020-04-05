package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

class StarterChainChestplate extends Armor {

    public StarterChainChestplate(){
        super(49);
    }

    @Override
    public void onHitTaken(EntityDamageByEntityEvent event) {
        super.onHitTaken(event);

        event.getDamager().setFireTicks(200);
    }
}
