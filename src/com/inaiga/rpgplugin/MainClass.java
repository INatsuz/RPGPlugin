package com.inaiga.rpgplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    @Override
    public void onDisable() {
        getLogger().info("Shutting down this bitch!");
    }

    @Override
    public void onEnable() {
        getLogger().info("Bom dia! Alegria!");
    }



}
