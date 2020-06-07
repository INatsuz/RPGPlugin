package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.customitems.weapons.UsableItem;
import com.inaiga.rpgplugin.player.RPGPlayer;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ClassUsableItem implements UsableItem {

	private ArrayList<RPGClass> classes = new ArrayList<>();

	public ClassUsableItem(RPGClass... classes) {
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
