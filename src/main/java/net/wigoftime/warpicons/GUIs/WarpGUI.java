package net.wigoftime.warpicons.GUIs;

import net.wigoftime.warpicons.GUIListener;
import net.wigoftime.warpicons.Main;
import net.wigoftime.warpicons.Warp;
import net.wigoftime.warpicons.WarpSection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WarpGUI implements GUIListener {
    final Player opener;
    final Inventory gui;
    final List<Warp> warps;
    public WarpGUI(@NotNull Player opener, @NotNull WarpSection warpSection) {
        this.opener = opener;

        double rows;
        int guiSize;
        switch (warpSection.layoutType) {
            case GAPPY:
                rows = (warpSection.warps.size() - 1) / 7.0;
                guiSize = ((int) Math.ceil(rows) * 9) + 18;
                if (guiSize > 54) guiSize = 54;

                this.gui = Bukkit.createInventory(null, guiSize, warpSection.guiTitle);

                AtomicInteger index = new AtomicInteger(10);
                AtomicInteger lastColumnIndex = new AtomicInteger(16);
                warpSection.warps.forEach(warp -> {
                    gui.setItem(index.getAndAdd(1), warp.icon);

                    // If reached end of column, place icons to next row
                    if (index.get() > lastColumnIndex.get()) {
                        index.getAndAdd(2);

                        // Update to the next row.
                        //  Current Slot Index / Max Column size + add additional row
                        int row = (int) Math.floor(index.get() / 8 + 1);

                        // Update intger for the last available slot for the current row
                        // If the index gets higher than the last available slot, this refresh section
                        // will repeat.
                        // Amount of row * maximum columns + amount of rows - 2
                        lastColumnIndex.set(row * 8 + row - 2);
                    }
                });
                break;
            default:
                rows = (warpSection.warps.size() - 1) / 9.0;
                guiSize = (int) Math.ceil(rows) * 9;
                if (guiSize > 54) guiSize = 54;

                this.gui = Bukkit.createInventory(null, guiSize, warpSection.guiTitle);
                warpSection.warps.stream().forEach(warp -> {
                    gui.addItem(warp.icon);
                });
                break;
        }

        this.warps = warpSection.warps;
    }

    public void opened() {
        Player player = Bukkit.getPlayer(opener.getUniqueId());
        player.openInventory(gui);
    }

    public void closed() {

    }

    @Override
    public void clicked(InventoryClickEvent clickEvent) {
        clickEvent.setCancelled(true);
        warps.forEach(warp -> {
            if (warp.icon.equals(clickEvent.getCurrentItem())) {
                opener.teleport(warp.location);
                opener.sendMessage(Main.config.warpMsg);
                opener.closeInventory();
                return;
            }
        });
    }
}