package me.marco.core.spawn;

import me.marco.core.McCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {
    private final McCore plugin;
    private Location spawn;

    public SpawnManager(McCore plugin) {
        this.plugin = plugin;
        loadSpawn();
    }

    private void loadSpawn() {
        FileConfiguration config = plugin.getConfig();
        if (config.contains("spawn")) {
            World world = Bukkit.getWorld(config.getString("spawn.world"));
            double x = config.getDouble("spawn.x");
            double y = config.getDouble("spawn.y");
            double z = config.getDouble("spawn.z");
            float yaw = (float) config.getDouble("spawn.yaw");
            float pitch = (float) config.getDouble("spawn.pitch");
            spawn = new Location(world, x, y, z, yaw, pitch);
        }
    }

    public void setSpawn(Location loc) {
        this.spawn = loc;
        FileConfiguration config = plugin.getConfig();
        config.set("spawn.world", loc.getWorld().getName());
        config.set("spawn.x", loc.getX());
        config.set("spawn.y", loc.getY());
        config.set("spawn.z", loc.getZ());
        config.set("spawn.yaw", loc.getYaw());
        config.set("spawn.pitch", loc.getPitch());
        plugin.saveConfig();
    }

    public Location getSpawn() {
        return spawn != null ? spawn : plugin.getServer().getWorlds().get(0).getSpawnLocation();
    }
}
