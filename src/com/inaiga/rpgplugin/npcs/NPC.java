package com.inaiga.rpgplugin.npcs;

import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NPC implements Interactable {

	public static NPC defaultNPC = new NPC(new ArrayList<>(Arrays.asList("Hello there", "Estão a fazer o que?", "Hey Ferb, já sei o que vamos fazer hoje", "The cake is a lie", "MY CABBAGES!!", "Yesterday you said tommorrow, so just do it", "It is what it is", "Let's get down to business", "Sim, diz que sim!")));
	private ArrayList<String> dialogs = new ArrayList<>();
	private EntityType entityType = EntityType.VILLAGER;

	public NPC(ArrayList<String> dialogs) {
		this.dialogs = dialogs;
	}

	@Override
	public void onInteract(PlayerInteractEntityEvent event) {
		Random random = new Random();
		event.getPlayer().sendMessage(dialogs.get(random.nextInt(dialogs.size())));
	}

	public ArrayList<String> getDialogs() {
		return dialogs;
	}

	public void setDialogs(ArrayList<String> dialogs) {
		this.dialogs = dialogs;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
}
