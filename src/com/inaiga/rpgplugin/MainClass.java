package com.inaiga.rpgplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    @Override
    public void onDisable() {
        getLogger().info("Shutting down...!");
    }

    @Override
    public void onEnable() {
        getLogger().info("RPGPlugin just started!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("class")) {

        }
        return super.onCommand(sender, command, label, args);
    }
}