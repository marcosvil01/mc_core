package me.marco.core.zone;

import me.marco.core.spawn.SpawnManager;
import me.marco.core.utils.ChatUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ZoneListener implements Listener {
    private final SpawnManager spawnManager;
    private final int radius = 50; // Configurable spawn zone radius

    public ZoneListener(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    private boolean isInZone(Location loc) {
        Location spawn = spawnManager.getSpawn();
        if (!loc.getWorld().equals(spawn.getWorld())) return false;
        return loc.distance(spawn) <= radius;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (isInZone(event.getBlock().getLocation()) && !event.getPlayer().hasPermission("core.admin")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatUtil.color("&cYou cannot break blocks in the spawn zone!"));
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (isInZone(event.getBlock().getLocation()) && !event.getPlayer().hasPermission("core.admin")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatUtil.color("&cYou cannot place blocks in the spawn zone!"));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();

        if (isInZone(from) && !isInZone(to) && !player.hasPermission("core.admin")) {
            event.setTo(from);
            player.sendMessage(ChatUtil.color("&cYou cannot leave the spawn area!"));
        }
    }
}
