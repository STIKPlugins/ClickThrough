package me.stokmenn.clickthrough.listeners;

import me.stokmenn.clickthrough.config.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickThroughSignListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;
        if (!(clickedBlock.getState() instanceof Sign)) return;

        Player player = event.getPlayer();
        if (Config.signIgnoreWhenPlayerSneaking && player.isSneaking()) return;

        Block block = getOppositeFaceSign(clickedBlock.getLocation(), event.getBlockFace());

        if (block.getState() instanceof Container container) {
            player.openInventory(container.getInventory());
        } else if (block.getType() == Material.ENDER_CHEST) {
            player.openInventory(player.getEnderChest());
        } else return;

        event.setCancelled(true);
    }

    private Block getOppositeFaceSign(Location location, BlockFace blockFace) {
        return location.getBlock().getRelative(blockFace.getOppositeFace(), 1);
    }
}
