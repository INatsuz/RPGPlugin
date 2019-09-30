package com.inaiga.rpgplugin.player;

import com.inaiga.rpgplugin.MainClass;
import com.inaiga.rpgplugin.characters.Character;
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

	private Character[] characters = new Character[MAX_CHARACTERS];
	private Character activeCharacter = null;

	public RPGPlayer(Player player) {
		this.player = player;
		loadCharacters();
	}

	public Character[] getCharacters() {
		return characters;
	}

	public Character getCharacter(int index) {
		return characters[index];
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

	public Character getActiveCharacter() {
		return activeCharacter;
	}

	public void setActiveCharacter(Character activeCharacter) {
		this.activeCharacter = activeCharacter;
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

	private void loadCharacters() {
		Character[] characters = new Character[MAX_CHARACTERS];

		try {
			File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString() + ".json");
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();

				BufferedWriter writer = new BufferedWriter(new FileWriter(file));

				JSONObject initialJson = new JSONObject();
				JSONArray emptyCharacterArray = new JSONArray();

				initialJson.put("characters", emptyCharacterArray);

				writer.write(initialJson.toJSONString());
				writer.close();
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				JSONParser parser = new JSONParser();
				Object object = parser.parse(reader);

				JSONArray characterArray = (JSONArray) ((JSONObject) object).get("characters");

				for (int i = 0; i < characterArray.size(); i++) {
					JSONObject characterJson = (JSONObject) characterArray.get(i);

					characters[i] = new Character(Class.valueOf(characterJson.get("class").toString()), Integer.parseInt(characterJson.get("level").toString()));

					System.out.println(characterJson.get("class"));
					System.out.println(characterJson.get("level"));
				}
			}

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		this.characters = characters;
	}

	public void saveCharacters() {
		JSONObject charactersObject = new JSONObject();
		JSONArray characterArray = new JSONArray();

		for (Character character : characters) {
			if (character != null) {
				JSONObject characterJson = new JSONObject();

				characterJson.put("class", character.getCharacterClass().toString());
				characterJson.put("level", character.getLevel());

				characterArray.add(characterJson);
			}
		}

		charactersObject.put("characters", characterArray);

		File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString() + ".json");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(charactersObject.toJSONString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean chooseCharacter(Character character) {
		for (Character arrayCharacter : characters) {
			if (arrayCharacter == character) {
				activeCharacter = character;
				return true;
			}
		}

		return false;
	}

	public boolean chooseCharacter(int index) {
		if (characters.length > index && characters[index] != null) {
			activeCharacter = characters[index];
			return true;
		}

		return false;
	}

}
