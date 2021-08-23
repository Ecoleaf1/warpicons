package net.wigoftime.warpicons.GUIs;

import net.wigoftime.warpicons.GUIListener;
import net.wigoftime.warpicons.Warp;
import net.wigoftime.warpicons.WarpSection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class GUIController {
    public static Map<UUID, GUIListener> activeListeners = new LinkedHashMap<>();

    public static @Nullable GUIListener open(@NotNull UUID targetUUID, @NotNull Class guiClass, @NotNull Object[] parameters) {
        if (activeListeners.containsKey(targetUUID)) {
            activeListeners.get(targetUUID).closed();
            activeListeners.remove(targetUUID);
        }

        GUIListener newListener;
        if (guiClass == WarpGUI.class) {
            Player targetPlayer = (Player) parameters[0];
            WarpSection warpSection = (WarpSection) parameters[1];
            newListener = new WarpGUI(targetPlayer, warpSection);
        } else {
            return null;
        }

        activeListeners.put(targetUUID, newListener);
        newListener.opened();
        return newListener;
    }

    public static void closing(@NotNull UUID targetUUID) {
        if (activeListeners.containsKey(targetUUID)) {
            activeListeners.get(targetUUID).closed();
            activeListeners.remove(targetUUID);
        }
    }

    public static void clicked(@NotNull InventoryClickEvent clickEvent) {
        if (activeListeners.containsKey(clickEvent.getWhoClicked().getUniqueId()))
            activeListeners.get(clickEvent.getWhoClicked().getUniqueId()).clicked(clickEvent);
    }

}
