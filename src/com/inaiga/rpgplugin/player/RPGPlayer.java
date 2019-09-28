package com.inaiga.rpgplugin.player;

import org.bukkit.entity.Player;

public class RPGPlayer {

    private Character[] characters = new Character[4];

    private Player player;

    public RPGPlayer(Character[] characters, Player player) {
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
