package com.inaiga.rpgplugin.player;

import com.inaiga.rpgplugin.MainClass;
import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.classes.Class;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RPGPlayer {

	private static final int MAX_CHARACTERS = 4;

	private Player player;

	private RPGCharacter[] RPGCharacters = new RPGCharacter[MAX_CHARACTERS];
	private RPGCharacter activeRPGCharacter = null;

	/**
	 * Constructor of the RPGPlayer
	 * @param player {@link org.bukkit.entity.Player}
	 * */
	public RPGPlayer(Player player) {
		this.player = player;
		loadCharacters();
	}

	/**
	 * Returns an Array of RPGCharacters
	 * @return an Array of {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 * */
	public RPGCharacter[] getRPGCharacters() {
		return RPGCharacters;
	}

	/**
	 * Returns an RPGCharacter
	 * @param index Index of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} as an int
	 * @return The respective {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 * */
	public RPGCharacter getCharacter(int index) {
		return RPGCharacters[index];
	}

	/**
	 * Sets the Array of RPGCharacters
	 * @param RPGCharacters Array of {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 * */
	public void setRPGCharacters(RPGCharacter[] RPGCharacters) {
		this.RPGCharacters = RPGCharacters;
	}

	/**
	 * Returns the Player of an RPGPlayer
	 * @return {@link org.bukkit.entity.Player} from the RPGPlayer
	 * */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the Player for an RPGPlayer
	 * @param player {@link org.bukkit.entity.Player} for the RPGPlayer
	 * */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Return the active RPGCharacter
	 * @return the active {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 * */
	public RPGCharacter getActiveRPGCharacter() {
		return activeRPGCharacter;
	}

	/**
	 * Sets the active RPGCharacter
	 * @param activeRPGCharacter  {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to set as currently active
	 * */
	public void setActiveRPGCharacter(RPGCharacter activeRPGCharacter) {
		this.activeRPGCharacter = activeRPGCharacter;
	}

	/**
	 * Add one RPGCharacter
	 * @param RPGCharacter {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to add
	 * */
	public void addCharacter(RPGCharacter RPGCharacter) {
		for (int i = 0; i < RPGCharacters.length; i++) {
			if (RPGCharacters[i] == null) {
				RPGCharacters[i] = RPGCharacter;
				player.sendMessage("Character successfully created!");
				return;
			}
		}

		player.sendMessage("Character could not be created!");
	}

	/**
	 * Delete one RPGCharacter
	 * @param index index of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to delete
	 * @return true if deleted successfully, or false otherwise
	 * */
	public boolean deleteCharacter(int index) {
		if (RPGCharacters[index] != null) {
			RPGCharacters[index] = null;
			return true;
		}
		return false;
	}

	/**
	 * Loads the RPGCharacters for an RPGPlayer
	 * */
	private void loadCharacters() {
		RPGCharacter[] RPGCharacters = new RPGCharacter[MAX_CHARACTERS];	//Creates an Array of RPGCharacters

		try {
			File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString() + ".json");	//Creates a File
			if (!file.exists()) {
				file.getParentFile().mkdirs();	//Creates the required folders
				file.createNewFile();	//Creates the file

				BufferedWriter writer = new BufferedWriter(new FileWriter(file));	//Creates a writer

				JSONObject initialJson = new JSONObject();	//Creates a JSON Object
				JSONArray emptyCharacterArray = new JSONArray();	//Creates a JSON Array

				initialJson.put("characters", emptyCharacterArray);	//Adds an empty array to JSON Object

				writer.write(initialJson.toJSONString());	//Write the file with JSON Object
				writer.close();	//Close the file
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(file));	//Creates a reader

				JSONParser parser = new JSONParser();	//Creates a parser
				Object object = parser.parse(reader);	//Parses the reader

				JSONArray characterArray = (JSONArray) ((JSONObject) object).get("characters");	//Gets the characters and puts them in a JSONArray

				for (int i = 0; i < characterArray.size(); i++) {
					JSONObject characterJson = (JSONObject) characterArray.get(i);	//Gets the Character on the respective index

					RPGCharacters[i] = new RPGCharacter(Class.valueOf(characterJson.get("class").toString()), Integer.parseInt(characterJson.get("level").toString()));	//Puts the Character in the Array of RPGCharacters

					System.out.println(characterJson.get("class"));
					System.out.println(characterJson.get("level"));
				}
			}

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		this.RPGCharacters = RPGCharacters;
	}

	/**
	 * Saves the RPGCharacters for an RPGPlayer
	 * */
	public void saveCharacters() {
		JSONObject charactersObject = new JSONObject();	//Creates a JSON Object
		JSONArray characterArray = new JSONArray();	//Creates a JSON Array

		for (RPGCharacter RPGCharacter : RPGCharacters) {
			if (RPGCharacter != null) {
				JSONObject characterJson = new JSONObject();	//Creates a JSON Object

				characterJson.put("class", RPGCharacter.getCharacterClass().toString());	//Adds the class to JSON Object
				characterJson.put("level", RPGCharacter.getLevel());	//Adds the level to JSON Object

				characterArray.add(characterJson);	//Adds the CharacterJson to a JSON Array
			}
		}

		charactersObject.put("characters", characterArray);	//Adds the JSON Array to a JSON Object

		File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString() + ".json");	//Creates a file

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));	//Creates a writer

			writer.write(charactersObject.toJSONString());	//Write the file with JSON Object
			writer.close();	//Close the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Sets the active RPGCharacter from the given RPGCharacter
     * @param RPGCharacter  {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to set as currently active
     * */
	public boolean chooseCharacter(RPGCharacter RPGCharacter) {
		for (RPGCharacter arrayRPGCharacter : RPGCharacters) {
			if (arrayRPGCharacter == RPGCharacter) {
				activeRPGCharacter = RPGCharacter;
				return true;
			}
		}

		return false;
	}

    /**
     * Sets the active RPGCharacter by index
     * @param index  index of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to set as currently active
     * */
	public boolean chooseCharacter(int index) {
		if (RPGCharacters.length > index && RPGCharacters[index] != null) {
			activeRPGCharacter = RPGCharacters[index];
			return true;
		}

		return false;
	}

}
