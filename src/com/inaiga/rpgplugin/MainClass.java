package com.inaiga.rpgplugin;

import com.inaiga.rpgplugin.characters.Character;
import com.inaiga.rpgplugin.characters.CharacterMenu;
import com.inaiga.rpgplugin.classes.Class;
import com.inaiga.rpgplugin.listeners.InventoryClickListener;
import com.inaiga.rpgplugin.listeners.LoginLogoutListener;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

	private static MainClass instance;

	@Override
	public void onDisable() {
		getLogger().info("Shutting down...!");
	}

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
		if (label.equalsIgnoreCase("class")) {
			PlayerManager.getRPGPlayerFromPlayer((Player) sender).addCharacter(new Character(Class.valueOf(args[0].toUpperCase()), 1));
			System.out.println("Class Command");

			return true;
		} else if (label.equalsIgnoreCase("checkchar")) {
			Character[] characters = PlayerManager.getRPGPlayerFromPlayer((Player) sender).getCharacters();
			for (Character character : characters) {
				if (character != null) {
					sender.sendMessage(character.getCharacterClass().toString());
				}
			}

			return true;
		} else if (label.equalsIgnoreCase("choosechar")) {
			RPGPlayer rpgPlayer = PlayerManager.getRPGPlayerFromPlayer((Player) sender);

			rpgPlayer.chooseCharacter(Integer.parseInt(args[0]));
			sender.sendMessage("Your active character is " + rpgPlayer.getActiveCharacter().getCharacterClass());

			return true;
		} else if (label.equalsIgnoreCase("pickchar")) {
			CharacterMenu.openCharacterMenu((Player) sender);

			return true;
		}

		return super.onCommand(sender, command, label, args);
	}
}