package com.henryrenyz.clib.listener;

import com.henryrenyz.clib.modules.customItem.ItemManager;
import com.henryrenyz.clib.modules.menu.MenuManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpen implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {

        //MenuManager;
        MenuManager.HandlerOpenInv(event);

    }

}
