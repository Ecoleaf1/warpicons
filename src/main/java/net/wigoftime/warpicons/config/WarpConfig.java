package net.wigoftime.warpicons.config;

import net.wigoftime.warpicons.Warp;
import net.wigoftime.warpicons.WarpSection;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Locale;

public class WarpConfig {
    @NotNull File file;

    public WarpConfig(@NotNull File file) {
        this.file = file;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        String subcommand = file.getName().replace(".yml", "").toLowerCase();
        String materialInString = configuration.getString("Icon Material");
        WarpSection.LayoutType layoutType = WarpSection.LayoutType.valueOf(configuration.getString("Layout").toUpperCase());
        String uiTitle = ChatColor.translateAlternateColorCodes('&',configuration.getString("UI Title"));
        String requiredPermissionNode = configuration.getString("Required Permission");
        if (requiredPermissionNode.equals("")) requiredPermissionNode = null;

        @NotNull WarpSection warpSection = WarpSection.createWarpSection(subcommand,requiredPermissionNode, uiTitle, layoutType);
        ConfigurationSection warpsConfigSection = configuration.getConfigurationSection("Warps");
        warpsConfigSection.getKeys(false).stream().forEach(warpConfigSection -> {
            ConfigurationSection section = warpsConfigSection.getConfigurationSection(warpConfigSection);
            String warpName = ChatColor.translateAlternateColorCodes('&',section.getString("Display Name"));
            Location location = section.getLocation("Location");
            int customModelData = section.getInt("CustomModelData");

            if (subcommand.equals("default")) WarpSection.hasDefaultSection = true;

            warpSection.addWarp(new Warp(warpName, location, Material.valueOf(materialInString), customModelData));
        });
    }
}
