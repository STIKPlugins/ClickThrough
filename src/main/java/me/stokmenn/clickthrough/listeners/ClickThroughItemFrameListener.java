package me.stokmenn.clickthrough.listeners;

import me.stokmenn.clickthrough.config.Config;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ClickThroughItemFrameListener implements Listener {
    private final JavaPlugin plugin;

    public ClickThroughItemFrameListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ItemFrame itemFrame)) return;
        if (Config.requiredItemInFrame && itemFrame.getItem().getType() == Material.AIR) return;

        Player player = event.getPlayer();
        if (Config.itemFrameIgnoreWhenPlayerSneaking && player.isSneaking()) return;

        Block block = player.getTargetBlockExact(6);

        if (block == null) return;
        if (block.getState() instanceof Container container) {
            player.openInventory(container.getInventory());
        } else if (block.getType() == Material.ENDER_CHEST) {
            player.openInventory(player.getEnderChest());
        } else return;

        Rotation rotation = itemFrame.getRotation();
        event.setCancelled(true);
        itemFrame.getScheduler().run(plugin, task -> itemFrame.setRotation(rotation), null);
    }
}
