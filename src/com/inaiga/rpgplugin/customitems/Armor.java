package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.classes.RPGClass;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.event.player.PlayerInteractEvent;

public class Armor implements HoldableItem{
    public int reduction_multiplier;

    public Armor(int reduction_multiplier){
        this.reduction_multiplier=reduction_multiplier;
    }   //custom constructor for armors

    public int getArmorValue(){
        return reduction_multiplier;
    }

    @Override
    public void onHold(PlayerInteractEvent event) {
        //to implement combos
    }

    @Override
    public int getArmorMultiplier() {
        return reduction_multiplier;
    }

}
