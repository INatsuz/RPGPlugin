package com.inaiga.rpgplugin.player;

import com.inaiga.rpgplugin.MainClass;
import com.inaiga.rpgplugin.characters.RPGCharacter;
import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.skills.Skills;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RPGPlayer {

	private static final int MAX_CHARACTERS = 4;

	private Player player;

	private RPGCharacter[] rpgCharacters = new RPGCharacter[MAX_CHARACTERS];
	private RPGCharacter activeRPGCharacter = null;

	private int[] abilitySequence = {-1, -1, -1, -1};
	private int currentSequenceIndex = 0;

	/**
	 * Constructor of the RPGPlayer
	 *
	 * @param player {@link org.bukkit.entity.Player}
	 */
	public RPGPlayer(Player player) {
		this.player = player;
		loadCharacters();
	}

	/**
	 * Returns an Array of rpgCharacters
	 *
	 * @return an Array of {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 */
	public RPGCharacter[] getRpgCharacters() {
		return rpgCharacters;
	}

	/**
	 * Returns an RPGCharacter
	 *
	 * @param index Index of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} as an int
	 * @return The respective {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 */
	public RPGCharacter getCharacter(int index) {
		return rpgCharacters[index];
	}

	/**
	 * Sets the Array of rpgCharacters
	 *
	 * @param rpgCharacters Array of {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 */
	public void setRpgCharacters(RPGCharacter[] rpgCharacters) {
		this.rpgCharacters = rpgCharacters;
	}

	/**
	 * Returns the Player of an RPGPlayer
	 *
	 * @return {@link org.bukkit.entity.Player} from the RPGPlayer
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the Player for an RPGPlayer
	 *
	 * @param player {@link org.bukkit.entity.Player} for the RPGPlayer
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Return the active RPGCharacter
	 *
	 * @return the active {@link com.inaiga.rpgplugin.characters.RPGCharacter}
	 */
	public RPGCharacter getActiveRPGCharacter() {
		return activeRPGCharacter;
	}

	/**
	 * Sets the active RPGCharacter
	 *
	 * @param activeRPGCharacter {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to set as currently active
	 */
	public void setActiveRPGCharacter(RPGCharacter activeRPGCharacter) {
		this.activeRPGCharacter = activeRPGCharacter;
	}

	/**
	 * Add one RPGCharacter
	 *
	 * @param RPGCharacter {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to add
	 */
	public void addCharacter(RPGCharacter RPGCharacter) {
		for (int i = 0; i < rpgCharacters.length; i++) {
			if (rpgCharacters[i] == null) {
				rpgCharacters[i] = RPGCharacter;
				player.sendMessage("Character successfully created!");
				return;
			}
		}

		player.sendMessage("Character could not be created!");
	}

	/**
	 * Delete one RPGCharacter
	 *
	 * @param index index of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to delete
	 * @return true if deleted successfully, or false otherwise
	 */
	public boolean deleteCharacter(int index) {
		if (rpgCharacters[index] != null) {
			rpgCharacters[index] = null;
			return true;
		}

		return false;
	}

	/**
	 * Delete one RPGCharacter
	 *
	 * @param RPGCharacter {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to delete
	 * @return true if deleted successfully, or false otherwise
	 */
	public boolean deleteCharacter(RPGCharacter RPGCharacter) {
		for (int i = 0; i < rpgCharacters.length; i++) {
			if (rpgCharacters[i] == RPGCharacter) {
				rpgCharacters[i] = null;
				return true;
			}
		}

		return false;
	}

	/**
	 * Loads the rpgCharacters for an RPGPlayer
	 */
	private void loadCharacters() {
		RPGCharacter[] RPGCharacters = new RPGCharacter[MAX_CHARACTERS];    //Creates an Array of rpgCharacters

		try {
			File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString() + ".json");    //Creates a File
			if (!file.exists()) {
				file.getParentFile().mkdirs();    //Creates the required folders
				file.createNewFile();    //Creates the file

				BufferedWriter writer = new BufferedWriter(new FileWriter(file));    //Creates a writer

				JSONObject initialJson = new JSONObject();    //Creates a JSON Object
				JSONArray emptyCharacterArray = new JSONArray();    //Creates a JSON Array

				initialJson.put("characters", emptyCharacterArray);    //Adds an empty array to JSON Object

				writer.write(initialJson.toJSONString());    //Write the file with JSON Object
				writer.close();    //Close the file
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(file));    //Creates a reader

				JSONParser parser = new JSONParser();    //Creates a parser
				Object object = parser.parse(reader);    //Parses the reader

				JSONArray characterArray = (JSONArray) ((JSONObject) object).get("characters");    //Gets the characters and puts them in a JSONArray

				for (int i = 0; i < characterArray.size(); i++) {
					JSONObject characterJson = (JSONObject) characterArray.get(i);    //Gets the Character on the respective index

					JSONArray skillsArray = (JSONArray) characterJson.get("skills");
					ArrayList<Skills> skills = new ArrayList<>();
					if(skillsArray != null && !skillsArray.isEmpty()) {
						for (int k = 0; k < skillsArray.size(); k++) {
							skills.add(Skills.valueOf((String) skillsArray.get(k)));
						}
					}

					RPGCharacters[i] = new RPGCharacter(RPGClass.valueOf(characterJson.get("class").toString()), Integer.parseInt(characterJson.get("level").toString()), skills);    //Puts the Character in the Array of rpgCharacters
				}
				reader.close();
			}

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		this.rpgCharacters = RPGCharacters;
	}

	/**
	 * Saves the rpgCharacters for an RPGPlayer
	 */
	public void saveCharacters() {
		JSONObject charactersObject = new JSONObject();    //Creates a JSON Object
		JSONArray characterArray = new JSONArray();    //Creates a JSON Array

		for (RPGCharacter rpgCharacter : rpgCharacters) {
			if (rpgCharacter != null) {
				JSONObject characterJson = new JSONObject();    //Creates a JSON Object

				characterJson.put("class", rpgCharacter.getCharacterRPGClass().toString());    //Adds the class to JSON Object
				characterJson.put("level", rpgCharacter.getLevel());    //Adds the level to JSON Object

				JSONArray characterSkills = new JSONArray();
				for (Skills skill : rpgCharacter.getSkills()) {
					characterSkills.add(skill.name());
				}
				characterJson.put("skills", characterSkills);

				characterArray.add(characterJson);    //Adds the CharacterJson to a JSON Array
			}
		}

		charactersObject.put("characters", characterArray);    //Adds the JSON Array to a JSON Object

		File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString() + ".json");    //Creates a file

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));    //Creates a writer

			writer.write(charactersObject.toJSONString());    //Write the file with JSON Object
			writer.close();    //Close the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the active RPGCharacter from the given RPGCharacter
	 *
	 * @param RPGCharacter {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to set as currently active
	 */
	public boolean chooseCharacter(RPGCharacter RPGCharacter) {
		for (RPGCharacter arrayRPGCharacter : rpgCharacters) {
			if (arrayRPGCharacter == RPGCharacter) {
				activeRPGCharacter = RPGCharacter;
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets the active RPGCharacter by index
	 *
	 * @param index index of the {@link com.inaiga.rpgplugin.characters.RPGCharacter} you want to set as currently active
	 */
	public boolean chooseCharacter(int index) {
		if (rpgCharacters.length > index && rpgCharacters[index] != null) {
			activeRPGCharacter = rpgCharacters[index];
			return true;
		}

		return false;
	}

	public void handleInteraction(boolean isRightClick) {
		if (currentSequenceIndex == 0) {
			abilitySequence = new int[]{-1, -1, -1, -1};
			if (isRightClick) {
				abilitySequence[currentSequenceIndex] = 1;
				currentSequenceIndex = ++currentSequenceIndex % abilitySequence.length;

				sendSequenceActionBar();
			}
		} else {
			abilitySequence[currentSequenceIndex] = isRightClick ? 1 : 0;
			currentSequenceIndex = ++currentSequenceIndex % abilitySequence.length;
			sendSequenceActionBar();

			if (currentSequenceIndex == 0) {
				for (Skills skill : getActiveRPGCharacter().getSkills()) {
					if (Arrays.equals(skill.getSequence(), abilitySequence)) {
						Skills.executeSkill(skill, player);
						break;
					}
				}
			}

		}
	}

	private void sendSequenceActionBar() {
		String command = "title " + player.getDisplayName() + " actionbar \"";
		for (int i : abilitySequence) {
			if (i == 1) {
				command += "Right ";
			} else if (i == 0) {
				command += "Left ";
			}
		}
		command += "\"";
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	public boolean isDoingAbility() {
		return currentSequenceIndex != 0;
	}

}
