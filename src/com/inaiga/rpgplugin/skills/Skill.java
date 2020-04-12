package com.inaiga.rpgplugin.skills;

import org.bukkit.entity.Player;

public interface Skill {

    public <T> void execute(Player player, T ...args);

}