package me.stokmenn.clickthrough.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    private static JavaPlugin plugin;

    public static boolean doClickThroughSign;
    public static boolean signIgnoreWhenPlayerSneaking;

    public static boolean doClickThroughItemFrame;
    public static boolean itemFrameIgnoreWhenPlayerSneaking;
    public static boolean requiredItemInFrame;

    public static Component noPermissionToReload;
    public static Component configReloaded;

    public static void init(JavaPlugin plugin) {
        Config.plugin = plugin;
        plugin.saveDefaultConfig();
        reload();
    }

    public static void reload() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        doClickThroughSign = config.getBoolean("sign.doClickThrough", true);
        signIgnoreWhenPlayerSneaking = config.getBoolean("sign.ignoreWhenPlayerSneaking", true);

        doClickThroughItemFrame = config.getBoolean("itemFrame.doClickThrough", true);
        itemFrameIgnoreWhenPlayerSneaking = config.getBoolean("itemFrame.ignoreWhenPlayerSneaking", true);
        requiredItemInFrame = config.getBoolean("itemFrame.requiredItemInFrame", true);

        noPermissionToReload = MiniMessage.miniMessage().deserialize(config.getString(
                "noPermissionToReload", "<red>✘ <white>You don't have permission to reload Config!"));

        configReloaded = MiniMessage.miniMessage().deserialize(config.getString(
                "configReloaded", "<green>✔ <white>Config reloaded!"));
    }
}