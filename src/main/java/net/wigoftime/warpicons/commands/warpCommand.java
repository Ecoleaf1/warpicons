package net.wigoftime.warpicons.commands;

import net.wigoftime.warpicons.Main;
import net.wigoftime.warpicons.WarpSection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class warpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "This command can only be ran by a player.");
            return true;
        }

        if (args.length < 1) {
            if (WarpSection.hasDefaultSection)
                WarpSection.openCategory(((Player) sender), "default");
            else
                sender.sendMessage(ChatColor.DARK_RED + "Sorry, but you need to specify a warp category");

            return true;
        }

        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("warpicons.reload")) {
            WarpSection.reload();
            sender.sendMessage(ChatColor.GREEN + "Warps reloaded");
            return true;
        }

        WarpSection.openCategory(((Player) sender),args[0].toLowerCase());
        return true;
    }
}
