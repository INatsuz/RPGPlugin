package com.inaiga.rpgplugin.skills;

import com.inaiga.rpgplugin.player.RPGPlayer;

public class Backstab implements Skill{

	@Override
	public <T> void execute(T ...args) {
		RPGPlayer rpgPlayer = (RPGPlayer) args[0];
	}

}
