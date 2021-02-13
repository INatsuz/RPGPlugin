package com.inaiga.rpgplugin.npcs;

import org.bukkit.event.player.PlayerInteractEntityEvent;

public interface Interactable {

	void onInteract(PlayerInteractEntityEvent event);

}
