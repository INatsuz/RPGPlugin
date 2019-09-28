package com.inaiga.rpgplugin;

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



}