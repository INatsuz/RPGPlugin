package com.inaiga.rpgplugin.player;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerManager {

	private static ArrayList<RPGPlayer> players = new ArrayList<>();

	public static void createPlayer(Player player) {
		players.add(new RPGPlayer(player));
	}

	public static ArrayList<RPGPlayer> getPlayers() {
		return players;
	}

	public static void setPlayers(ArrayList<RPGPlayer> players) {
		PlayerManager.players = players;
	}

	public static RPGPlayer getRPGPlayerFromPlayer(Player player) {
        for (RPGPlayer rpgPlayer : players) {
            if (rpgPlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                return rpgPlayer;
            }
        }
        return null;
    }

}
