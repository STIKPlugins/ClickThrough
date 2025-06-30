package me.stokmenn.clickthrough.commands;

import me.stokmenn.clickthrough.ClickThrough;
import me.stokmenn.clickthrough.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final ClickThrough plugin;

    public ReloadCommand(ClickThrough plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("clickThrough.reload")) {
            sender.sendMessage(Config.noPermissionToReload);
            return false;
        }
        boolean oldDoClickThroughSign = Config.doClickThroughSign;
        boolean oldDoClickThroughItemFrame = Config.doClickThroughItemFrame;

        Bukkit.getAsyncScheduler().runNow(plugin, task -> {
            Config.reload();

            if (oldDoClickThroughItemFrame != Config.doClickThroughItemFrame) {
                if (Config.doClickThroughItemFrame) {
                    Bukkit.getPluginManager().registerEvents(plugin.getClickThroughItemFrameListener(), plugin);
                } else {
                    HandlerList.unregisterAll(plugin.getClickThroughItemFrameListener());
                }
            }
            if (oldDoClickThroughSign != Config.doClickThroughSign) {
                if (Config.doClickThroughSign) {
                    Bukkit.getPluginManager().registerEvents(plugin.getClickThroughSignListener(), plugin);
                } else {
                    HandlerList.unregisterAll(plugin.getClickThroughSignListener());
                }
            }
        });

        sender.sendMessage(Config.configReloaded);
        return true;
    }
}