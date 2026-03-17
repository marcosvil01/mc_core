package me.marco.core.portals;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.marco.core.McCore;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;
import java.util.Map;

public class PortalManager implements Listener {
    private final McCore plugin;
    private final Map<String, String> portalDestinations = new HashMap<>();

    public PortalManager(McCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        loadPortals();
    }

    private void loadPortals() {
        FileConfiguration config = plugin.getConfig();
        if (config.getConfigurationSection("portals") != null) {
            for (String key : config.getConfigurationSection("portals").getKeys(false)) {
                portalDestinations.put(key.toLowerCase(), config.getString("portals." + key));
            }
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) return;

        Player player = event.getPlayer();
        // Simple logic: check world name or use a specific region if we had WorldEdit integration here
        // For now, let's assume all nether portals in lobby send to a default or specific server based on location
        
        event.setCancelled(true);
        sendToHub(player, "survival"); // Default example
    }

    public void sendToHub(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
