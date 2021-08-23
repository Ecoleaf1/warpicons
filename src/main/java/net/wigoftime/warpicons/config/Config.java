package net.wigoftime.warpicons.config;

import net.wigoftime.warpicons.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Objects;

public class Config {
    public final String resourcePackURL;
    public final String warpMsg;

    public Config(File config) {
        if (!config.exists()) generateConfig();

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(config);
        resourcePackURL = configuration.getString("Resourcepack");
        warpMsg = ChatColor.translateAlternateColorCodes('&', configuration.getString("Teleport Message"));
    }

    private void generateConfig() {
        try {
            InputStream stream = Main.class.getResourceAsStream("default-config/config.yml");
            Files.copy(stream, Paths.get(Main.main.getDataFolder() + "/config.yml"), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException exception) {
            exception.printStackTrace();
            Bukkit.getLogger().info(ChatColor.DARK_RED + "COULD NOT GENERATE DEFAULT CONFIG FILE");
        }
    }
}
