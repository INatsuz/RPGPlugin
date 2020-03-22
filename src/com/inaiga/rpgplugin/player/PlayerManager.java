package com.inaiga.rpgplugin.player;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class PlayerManager {

	private static ArrayList<RPGPlayer> players = new ArrayList<>();

	/**
	 * Creates a new RPGPlayer
	 * @param player {@link org.bukkit.entity.Player} used to create an {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static void createPlayer(Player player) {
		players.add(new RPGPlayer(player));
	}

	public static void removePlayer(RPGPlayer rpgPlayer) {
		players.remove(rpgPlayer);
	}

	/**
	 * Returns the List of online RPGPlayers
	 * @return {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static ArrayList<RPGPlayer> getPlayers() {
		return players;
	}

	/**
	 * Sets the List of online RPGPlayers
	 * @param players {@link java.util.ArrayList} of {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static void setPlayers(ArrayList<RPGPlayer> players) {
		PlayerManager.players = players;
	}

	/**
	 * Returns an RPGPlayer from a Player
	 * @param player {@link org.bukkit.entity.Player} used to return the respective {@link com.inaiga.rpgplugin.player.RPGPlayer}
	 * */
	public static RPGPlayer getRPGPlayerFromPlayer(Player player) {
        for (RPGPlayer rpgPlayer : players) {
            if (rpgPlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                return rpgPlayer;
            }
        }
        return null;
    }

}