package me.marco.core.perms;

import me.marco.core.McCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PermissionManager {
    private final File file;
    private final FileConfiguration config;
    private final Map<UUID, String> ranks = new HashMap<>();

    public PermissionManager(McCore plugin) {
        this.file = new File(plugin.getDataFolder(), "permissions.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if (config.getConfigurationSection("players") == null) return;
        for (String key : config.getConfigurationSection("players").getKeys(false)) {
            ranks.put(UUID.fromString(key), config.getString("players." + key));
        }
    }

    public void save() {
        for (Map.Entry<UUID, String> entry : ranks.entrySet()) {
            config.set("players." + entry.getKey().toString(), entry.getValue());
        }
        try { config.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public String getRank(UUID uuid) {
        return ranks.getOrDefault(uuid, "MEMBER");
    }

    public void setRank(UUID uuid, String rank) {
        ranks.put(uuid, rank.toUpperCase());
        save();
    }

    public boolean hasPermission(Player player, String permission) {
        String rank = getRank(player.getUniqueId());
        if (rank.equals("ADMIN")) return true;
        if (rank.equals("VIP") && permission.startsWith("core.vip")) return true;
        return player.hasPermission(permission);
    }
}
