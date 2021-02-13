package com.inaiga.rpgplugin.npcs;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;

public class NPCManager {

	private static HashMap<NPC, Entity> entities = new HashMap<>();

	public static void spawnNPC(NPC npc, Location location){
		Entity entity = location.getWorld().spawnEntity(location, npc.getEntityType());
		entities.put(npc, entity);
	}

	public static HashMap<NPC, Entity> getEntities() {
		return entities;
	}
}
