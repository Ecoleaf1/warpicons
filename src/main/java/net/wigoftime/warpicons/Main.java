package net.wigoftime.warpicons;

import net.wigoftime.warpicons.commands.warpCommand;
import net.wigoftime.warpicons.config.Config;
import net.wigoftime.warpicons.config.FileCreation;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main main;
    public static Config config;

    public void onEnable() {
        main = this;
        new FileCreation();
        getCommand("warp").setExecutor(new warpCommand());

        Listener listener = new Listener() {
        };
        Bukkit.getPluginManager().registerEvent(InventoryCloseEvent.class, listener, EventPriority.LOW, new net.wigoftime.warpicons.events.InventoryCloseEvent(), this);
        Bukkit.getPluginManager().registerEvent(InventoryClickEvent.class, listener, EventPriority.LOW, new net.wigoftime.warpicons.events.InventoryClickEvent(), this);

        Bukkit.getLogger().info("Warp icons enabled!");
    }

    public void onDisable() {
        Bukkit.getLogger().info("Warp icons disabled!");
    }
}
