package com.inaiga.rpgplugin.player;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.entity.Player;

public class PlayerManager {

	private static HashMap<Player, RPGPlayer> players = new HashMap<>();

	/**
	 * Creates a new RPGPlayer
	 * @param player {@link org.bukkit.entity.Player} used to create an {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static void createPlayer(Player player) {
		players.put(player, new RPGPlayer(player));
	}

	public static void removePlayer(Player player) {
		players.remove(player);
	}

	/**
	 * Returns the List of online RPGPlayers
	 * @return {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static ArrayList<RPGPlayer> getPlayers() {
		return new ArrayList<>(players.values());
	}

	/**
	 * Sets the List of online RPGPlayers
	 * @param players {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static void setPlayers(HashMap<Player, RPGPlayer> players) {
		PlayerManager.players = players;
	}

	/**
	 * Returns an RPGPlayer from a Player
	 * @param player {@link org.bukkit.entity.Player} used to return the respective {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static RPGPlayer getRPGPlayerFromPlayer(Player player) {
        return players.get(player);
    }

}