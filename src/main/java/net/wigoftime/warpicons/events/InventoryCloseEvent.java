package net.wigoftime.warpicons.events;

import net.wigoftime.warpicons.GUIs.GUIController;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

public class InventoryCloseEvent implements EventExecutor {

    @Override
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
        org.bukkit.event.inventory.InventoryCloseEvent closeEvent = (org.bukkit.event.inventory.InventoryCloseEvent) event;
        GUIController.closing(closeEvent.getPlayer().getUniqueId());
    }
}
