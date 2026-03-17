package me.marco.core.v2.portals;

import me.marco.core.v2.selection.SelectionManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class ModernPortalManager implements Listener {
    private final Map<String, PortalRegion> regions = new HashMap<>();

    public void createPortal(String name, Location p1, Location p2, String targetServer) {
        regions.put(name.toLowerCase(), new PortalRegion(p1, p2, targetServer));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        if (to == null) return;
        
        for (PortalRegion region : regions.values()) {
            if (region.contains(to)) {
                // Send to BungeeCord here (similar to legacy but with selection)
                player.sendMessage("§bPortal detectado! Teletransportando a " + region.targetServer);
            }
        }
    }

    private static class PortalRegion {
        private final Location p1, p2;
        public final String targetServer;

        public PortalRegion(Location p1, Location p2, String targetServer) {
            this.p1 = p1;
            this.p2 = p2;
            this.targetServer = targetServer;
        }

        public boolean contains(Location loc) {
            double minX = Math.min(p1.getX(), p2.getX());
            double maxX = Math.max(p1.getX(), p2.getX());
            double minY = Math.min(p1.getY(), p2.getY());
            double maxY = Math.max(p1.getY(), p2.getY());
            double minZ = Math.min(p1.getZ(), p2.getZ());
            double maxZ = Math.max(p1.getZ(), p2.getZ());
            return loc.getX() >= minX && loc.getX() <= maxX &&
                   loc.getY() >= minY && loc.getY() <= maxY &&
                   loc.getZ() >= minZ && loc.getZ() <= maxZ &&
                   loc.getWorld().equals(p1.getWorld());
        }
    }
}
