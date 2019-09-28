package com.inaiga.rpgplugin.player;

import com.inaiga.rpgplugin.MainClass;
import org.bukkit.entity.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class PlayerManager {

    public static ArrayList<RPGPlayer> players = new ArrayList<>();

    private static void loadPlayer(Player player) {
        File file = new File(MainClass.getInstance().getDataFolder() + File.separator + "characters" + File.separator + player.getUniqueId().toString());

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void createPlayer(Player player) {

        players.add(new RPGPlayer());
    }

}
