package net.wigoftime.warpicons;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class Warp {
    public final String displayName;
    public final Location location;
    public final ItemStack icon;

    public Warp(@NotNull String displayName, @NotNull Location location, Material material, @NotNull int customModelData) {
        this.displayName = displayName;
        this.location = location;

        ItemStack icon = new ItemStack(material);
        ItemMeta meta = icon.getItemMeta();
        meta.setCustomModelData(customModelData);
        meta.setDisplayName(displayName);
        icon.setItemMeta(meta);

        this.icon = icon;
    }
}
