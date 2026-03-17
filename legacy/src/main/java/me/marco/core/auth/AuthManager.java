package me.marco.core.auth;

import me.marco.core.McCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthManager {
    private final File file;
    private final FileConfiguration config;
    private final Map<UUID, String> passwords = new HashMap<>();
    private final Map<UUID, Boolean> premium = new HashMap<>();
    private final Map<UUID, Boolean> authenticated = new HashMap<>();

    public AuthManager(McCore plugin) {
        this.file = new File(plugin.getDataFolder(), "auth.yml");
        if (!file.exists()) {
            plugin.saveResource("auth.yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
        load();
    }

    private void load() {
        if (config.getConfigurationSection("users") != null) {
            for (String key : config.getConfigurationSection("users").getKeys(false)) {
                passwords.put(UUID.fromString(key), config.getString("users." + key + ".password"));
                premium.put(UUID.fromString(key), config.getBoolean("users." + key + ".premium", false));
            }
        }
    }

    public void save() {
        for (UUID uuid : passwords.keySet()) {
            config.set("users." + uuid.toString() + ".password", passwords.get(uuid));
            config.set("users." + uuid.toString() + ".premium", premium.getOrDefault(uuid, false));
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRegistered(UUID uuid) {
        return passwords.containsKey(uuid);
    }

    public boolean isAuthenticated(UUID uuid) {
        return authenticated.getOrDefault(uuid, false);
    }

    public void setAuthenticated(UUID uuid, boolean auth) {
        authenticated.put(uuid, auth);
    }

    public void register(UUID uuid, String password) {
        passwords.put(uuid, password);
        save();
    }

    public boolean checkPassword(UUID uuid, String password) {
        return passwords.getOrDefault(uuid, "").equals(password);
    }

    public boolean isPremium(UUID uuid) {
        return premium.getOrDefault(uuid, false);
    }

    public void setPremium(UUID uuid, boolean isPremium) {
        premium.put(uuid, isPremium);
        save();
    }
}
