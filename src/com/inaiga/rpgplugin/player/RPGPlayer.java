package com.inaiga.rpgplugin.player;

import org.bukkit.entity.Player;

public class RPGPlayer {

    private Character[] characters;

    private Player player;

    public RPGPlayer(Player player, Character[] characters) {
        this.characters = characters;
        this.player = player;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
