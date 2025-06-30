package me.stokmenn.clickthrough;

import me.stokmenn.clickthrough.commands.ReloadCommand;
import me.stokmenn.clickthrough.config.Config;
import me.stokmenn.clickthrough.listeners.ClickThroughItemFrameListener;
import me.stokmenn.clickthrough.listeners.ClickThroughSignListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClickThrough extends JavaPlugin {
    private ClickThroughItemFrameListener clickThroughItemFrameListener;
    private ClickThroughSignListener clickThroughSignListener;

    @Override
    public void onEnable() {
        Config.init(this);
        clickThroughItemFrameListener = new ClickThroughItemFrameListener(this);
        clickThroughSignListener = new ClickThroughSignListener();

        getCommand("clickthrough").setExecutor(new ReloadCommand(this));
        if (Config.doClickThroughItemFrame) {
            Bukkit.getPluginManager().registerEvents(clickThroughItemFrameListener, this);
        } else {
            HandlerList.unregisterAll(clickThroughItemFrameListener);
        }
        if (Config.doClickThroughSign) {
            Bukkit.getPluginManager().registerEvents(clickThroughSignListener, this);
        } else {
            HandlerList.unregisterAll(clickThroughSignListener);
        }
    }

    public ClickThroughItemFrameListener getClickThroughItemFrameListener() {
        return clickThroughItemFrameListener;
    }

    public ClickThroughSignListener getClickThroughSignListener() {
        return clickThroughSignListener;
    }
}
