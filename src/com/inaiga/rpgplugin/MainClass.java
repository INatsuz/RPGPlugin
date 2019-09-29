package com.inaiga.rpgplugin;

import com.inaiga.rpgplugin.listeners.LoginListener;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.character.Character;
import com.inaiga.rpgplugin.classes.Class;
import com.inaiga.rpgplugin.skills.Skill;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class MainClass extends JavaPlugin {

    private static MainClass instance;

    @Override
    public void onDisable() {
        getLogger().info("Shutting down...!");
    }

    @Override
    public void onEnable() {
        getLogger().info("RPGPlugin just started!");
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        instance = this;
    }

    public static MainClass getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("class")) {
            PlayerManager.getRPGPlayerFromPlayer((Player)sender).addCharacter(new Character(Class.valueOf(args[1].toUpperCase()), 1, new ArrayList<Skill>()));
        }
        return super.onCommand(sender, command, label, args);
    }
}