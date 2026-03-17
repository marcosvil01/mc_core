package me.marco.core;

import me.marco.core.abilities.AbilityManager;
import me.marco.core.auth.AuthCommands;
import me.marco.core.auth.AuthListener;
import me.marco.core.auth.AuthManager;
import me.marco.core.commands.CommandFilter;
import me.marco.core.messaging.AnnouncementManager;
import me.marco.core.perms.PermissionManager;
import me.marco.core.perms.RankCommand;
import me.marco.core.portals.PortalManager;
import me.marco.core.spawn.SpawnCommands;
import me.marco.core.spawn.SpawnManager;
import me.marco.core.commands.VanishCommand;
import me.marco.core.utils.ChatUtil;
import me.marco.core.zone.ZoneListener;
import org.bukkit.plugin.java.JavaPlugin;

public class McCore extends JavaPlugin {

    private static McCore instance;
    private AuthManager authManager;
    private SpawnManager spawnManager;
    private PermissionManager permissionManager;
    private PortalManager portalManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Managers
        this.authManager = new AuthManager(this);
        this.spawnManager = new SpawnManager(this);
        this.permissionManager = new PermissionManager(this);
        this.portalManager = new PortalManager(this);

        // Commands
        getCommand("login").setExecutor(new AuthCommands(authManager));
        getCommand("register").setExecutor(new AuthCommands(authManager));
        getCommand("setspawn").setExecutor(new SpawnCommands(spawnManager));
        getCommand("spawn").setExecutor(new SpawnCommands(spawnManager));
        getCommand("premium").setExecutor(new AuthCommands(authManager));
        getCommand("rank").setExecutor(new RankCommand(permissionManager));
        getCommand("vanish").setExecutor(new VanishCommand());

        // Listeners
        getServer().getPluginManager().registerEvents(new AuthListener(authManager), this);
        getServer().getPluginManager().registerEvents(new ZoneListener(spawnManager), this);
        getServer().getPluginManager().registerEvents(new AbilityManager(spawnManager), this);
        getServer().getPluginManager().registerEvents(new CommandFilter(), this);
        getServer().getPluginManager().registerEvents(portalManager, this);

        // Messaging
        new AnnouncementManager(this);
        
        getLogger().info("McCore Legacy (Mega-Core) activado correctamente!");
    }

    @Override
    public void onDisable() {
        authManager.save();
        permissionManager.save();
        getLogger().info("McCore Legacy desactivado.");
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public static McCore getInstance() {
        return instance;
    }
}
