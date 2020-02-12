package com.inaiga.rpgplugin;

import com.inaiga.rpgplugin.characters.Character;
import com.inaiga.rpgplugin.classes.Class;
import com.inaiga.rpgplugin.listeners.InventoryClickListener;
import com.inaiga.rpgplugin.listeners.LoginLogoutListener;
import com.inaiga.rpgplugin.menus.MenuManager;
import com.inaiga.rpgplugin.menus.MenuType;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

	private static MainClass instance;

	//Close plugin
	@Override
	public void onDisable() {
		getLogger().info("Shutting down...!");
	}

	//Start plugin
	@Override
	public void onEnable() {
		getLogger().info("RPGPlugin just started!");
		getServer().getPluginManager().registerEvents(new LoginLogoutListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		instance = this;
	}

	public static MainClass getInstance() {
		return instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Creates a RPG Character
		if (label.equalsIgnoreCase("class")) {
			RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer((Player) sender);	//Get the RPG Player

			if (rpgPlayer != null) {
				rpgPlayer.addCharacter(new Character(Class.valueOf(args[0].toUpperCase()), 1));	//Add an RPG Character
			}

			return true;
		//Checks the classes of your characters
		} else if (label.equalsIgnoreCase("checkchar")) {
			RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer((Player) sender);	//Get the RPG Player

			if (rpgPlayer != null) {
				Character[] characters = rpgPlayer.getCharacters();	//Get the RPG Characters of the RPG Player
				for (Character character : characters) {
					if (character != null) {
						sender.sendMessage(character.getCharacterClass().toString());	//Sends the Characters to the RPG Player
					}
				}
			}

			return true;
		//Chooses a character and sets it to your active character
		} else if (label.equalsIgnoreCase("choosechar")) {
			RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer((Player) sender);	//Get the RPG Player

			if (rpgPlayer != null) {
				if (rpgPlayer.chooseCharacter(Integer.parseInt(args[0]))) {
					sender.sendMessage("Your active character is " + rpgPlayer.getActiveCharacter().getCharacterClass());	//Set the RPG Character to active
				} else {
					sender.sendMessage("It wasn't possible to choose the character");	//Error
				}
			}

			return true;
		//Opens the RPG Characters Selection Menu
		} else if (label.equalsIgnoreCase("pickchar")) {
			MenuManager.openMenuForPlayer((Player) sender, MenuType.CHARACTER_SELECTION_MENU);	//Opens the Menu

			return true;
		//Deletes a RPG Character
		} else if (label.equalsIgnoreCase("delchar")) {
			RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer((Player) sender);	//Get the RPG Player

			if (rpgPlayer != null) {
				if(rpgPlayer.deleteCharacter(Integer.parseInt(args[0]))) {
					sender.sendMessage("Deleted Character successfully");	//Deleted the RPG Character
				}
				else {
					sender.sendMessage("It wasn't possible to delete the character");	//Error
				}
			}

			return true;
		}

		return super.onCommand(sender, command, label, args);	//Returns something
	}
}