package me.marco.core.abilities;

import me.marco.core.spawn.SpawnManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbilityManager implements Listener {
    private final SpawnManager spawnManager;
    private final int radius = 50;

    public AbilityManager(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    private boolean isInZone(Location loc) {
        Location spawn = spawnManager.getSpawn();
        if (spawn == null || !loc.getWorld().equals(spawn.getWorld())) return false;
        return loc.distance(spawn) <= radius;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (isInZone(player.getLocation())) {
            // Speed Boost
            if (!player.hasPotionEffect(PotionEffectType.SPEED)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            }
            // Enable flight for double jump
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.setAllowFlight(true);
            }
        } else {
            // Remove effects outside zone
            if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                player.removePotionEffect(PotionEffectType.SPEED);
            }
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        
        if (isInZone(player.getLocation())) {
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1.0));
            // Small delay to allow flight again (re-enabled by PlayerMoveEvent)
        }
    }
}
