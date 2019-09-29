package com.inaiga.rpgplugin.player;

import com.inaiga.rpgplugin.MainClass;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PlayerManager {

	public static final int MAX_CHARACTERS = 4;

	public static ArrayList<RPGPlayer> players = new ArrayList<>();

	private static Character[] loadPlayerCharacters(Player player) {
		Character[] characters = new Character[MAX_CHARACTERS];

		try {
			File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();

				BufferedWriter writer = new BufferedWriter(new FileWriter(file));

				JSONObject initialJson = new JSONObject();
				JSONArray emptyCharacterArray = new JSONArray();

				initialJson.put("characters", emptyCharacterArray);

				writer.write(initialJson.toJSONString());
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				JSONParser parser = new JSONParser();
				Object object = parser.parse(reader);

				JSONArray characterArray = (JSONArray) ((JSONObject) object).get("characters");

				for (int i = 0; i < characterArray.size(); i++) {
					JSONObject characterJson = (JSONObject) characterArray.get(i);

					System.out.println(characterJson.get("class"));
					System.out.println(characterJson.get("xp"));
				}
			}

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		return characters;
	}

	public static void createPlayer(Player player) {
		players.add(new RPGPlayer(player, loadPlayerCharacters(player)));
	}

}
