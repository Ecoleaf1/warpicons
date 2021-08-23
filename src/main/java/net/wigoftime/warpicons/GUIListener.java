package net.wigoftime.warpicons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface GUIListener {
    void opened();
    void closed();
    void clicked(InventoryClickEvent clickEvent);
}
