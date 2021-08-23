package net.wigoftime.warpicons.events;

import net.wigoftime.warpicons.Main;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinEvent implements EventExecutor {
    @Override
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
        org.bukkit.event.player.PlayerJoinEvent joinEvent = (org.bukkit.event.player.PlayerJoinEvent) event;

        if (!Main.config.resourcePackURL.equals("")) joinEvent.getPlayer().setResourcePack(Main.config.resourcePackURL);
    }
}
