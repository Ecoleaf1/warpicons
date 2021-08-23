package net.wigoftime.warpicons;

import net.wigoftime.warpicons.GUIs.GUIController;
import net.wigoftime.warpicons.GUIs.WarpGUI;
import net.wigoftime.warpicons.config.FileCreation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class WarpSection {
    public enum LayoutType {DEFAULT, GAPPY}

    public static boolean hasDefaultSection = false;

    @Nullable final String requiredPermission;
    @Nullable public final String guiTitle;
    public final LayoutType layoutType;

    public @NotNull List<Warp> warps = new LinkedList<>();

    private static @NotNull Map<String, WarpSection> warpSections = new LinkedHashMap<>();

    private WarpSection(@Nullable String requiredPermission, @Nullable String guiTitle, LayoutType layout) {
        this.requiredPermission = requiredPermission;
        this.guiTitle = guiTitle;
        this.layoutType = layout;
    }

    public void addWarp(@NotNull Warp warp) {
        warps.add(warp);
    }

    public static WarpSection getSection(String category) {
        return warpSections.get(category);
    }

    public static @NotNull WarpSection createWarpSection(@NotNull String category, @Nullable String permissionNode, @Nullable String guiTitle, @NotNull LayoutType layoutType) {
        WarpSection section = new WarpSection(permissionNode, guiTitle, layoutType);
        warpSections.put(category, section);
        return section;
    }

    public static void openCategory(@NotNull Player targetPlayer, @NotNull String category) {
        WarpSection selectedSection = warpSections.get(category);
        if (selectedSection == null) {
            targetPlayer.sendMessage(ChatColor.DARK_RED + "Warp category not found");
            return;
        }

        if (selectedSection.requiredPermission != null)
        if (!targetPlayer.hasPermission(selectedSection.requiredPermission)) {
            targetPlayer.sendMessage(ChatColor.DARK_RED + "Sorry, but you do not have permission to open this.");
            return;
        }

       GUIController.open(targetPlayer.getUniqueId(), WarpGUI.class, Arrays.asList(targetPlayer, selectedSection).toArray());
    }

    public static void reload() {
        warpSections.clear();
        hasDefaultSection = false;
        new FileCreation();
    }
}
