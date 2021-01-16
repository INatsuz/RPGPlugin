package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.player.RPGPlayer;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ClassItem implements CustomItem {

	private ArrayList<RPGClass> classes = new ArrayList<>();

	public ClassItem(RPGClass... classes) {
		this.classes.addAll(Arrays.asList(classes.length > 0 ? classes : RPGClass.values()));
	}

	public boolean isPlayerAllowedToUseItem(RPGPlayer rpgPlayer){
		if (rpgPlayer.getActiveRPGCharacter() != null) {
			return classes.contains(rpgPlayer.getActiveRPGCharacter().getCharacterRPGClass());
		} else {
			return false;
		}
	}

}
