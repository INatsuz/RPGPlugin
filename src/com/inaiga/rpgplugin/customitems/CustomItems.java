package com.inaiga.rpgplugin.customitems;

import com.inaiga.rpgplugin.menus.MenuManager;
import com.inaiga.rpgplugin.menus.MenuType;
import com.inaiga.rpgplugin.player.PlayerManager;
import com.inaiga.rpgplugin.player.RPGPlayer;
import org.bukkit.Material;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItems implements CustomItem {
    STARTER_STAFF_ITEM("Starter Staff", Material.BAMBOO){
        @Override
        public void onHit(PlayerInteractEvent event){
            RPGPlayer Rpgplayer= PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());
            switch(Rpgplayer.getActiveRPGCharacter().getCharacterClass()){
                case WIZARD:
                    event.getPlayer().damage(50);
                    break;
                case ROGUE:
                    event.getPlayer().damage(3);
                    break;
                default:
                    event.getPlayer().damage(9);
                    break;
            }
        }

        @Override
        public void onUse(PlayerInteractEvent event){
            RPGPlayer Rpgplayer= PlayerManager.getRPGPlayerFromPlayer(event.getPlayer());
            event.getPlayer().sendMessage("Using"+ Rpgplayer.getActiveRPGCharacter().getClass());
        }
    },
    CHARACTER_MENU_ITEM("Menu", Material.COMPASS){
        @Override
        public void onHit(PlayerInteractEvent event) {

        }

        @Override
        public void onUse(PlayerInteractEvent event) {
            MenuManager.openMenuForPlayer(event.getPlayer(), MenuType.CHARACTER_SELECTION_MENU);
            event.getPlayer().sendMessage("Opening Character Menu");
        }
    };

    private final String name;
    private final Material material;

    CustomItems(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public static ItemStack buildCustomItem(CustomItems item){
        ItemStack itemStack = new ItemStack(item.getMaterial(), 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(item.getName());
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

}
