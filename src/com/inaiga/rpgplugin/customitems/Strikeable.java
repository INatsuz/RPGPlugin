package com.inaiga.rpgplugin.customitems;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface Strikeable extends CustomItem {

	void onStrike(EntityDamageByEntityEvent event);

}
