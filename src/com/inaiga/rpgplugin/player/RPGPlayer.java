package com.inaiga.rpgplugin.player;

import org.bukkit.entity.Player;

public class RPGPlayer {

    private Character[] characters = new Character[PlayerManager.MAX_CHARACTERS];

    private Player player;

    public RPGPlayer(Player player, Character[] characters) {
        this.characters = characters;
        this.player = player;
    }

    public void addCharacter(Character character) {

        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == null) {
                characters[i] = character;
                player.sendMessage("Character successfully created!");
                return;
            }
        }
        player.sendMessage("Character could not be created!");
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
