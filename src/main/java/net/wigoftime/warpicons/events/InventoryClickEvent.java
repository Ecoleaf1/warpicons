package net.wigoftime.warpicons.events;

import net.wigoftime.warpicons.GUIs.GUIController;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

public class InventoryClickEvent implements EventExecutor {

    @Override
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
        org.bukkit.event.inventory.InventoryClickEvent clickEvent = (org.bukkit.event.inventory.InventoryClickEvent) event;

        Inventory clickedInventory = clickEvent.getClickedInventory();
        if (clickedInventory == null || clickEvent.getCurrentItem() == null) return;
        GUIController.clicked(clickEvent);
    }
}
