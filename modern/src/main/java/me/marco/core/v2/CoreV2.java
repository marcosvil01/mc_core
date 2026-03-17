package me.marco.core.v2;

import me.marco.core.v2.admin.AdminCommands;
import me.marco.core.v2.admin.AdminManager;
import me.marco.core.v2.database.DatabaseManager;
import me.marco.core.v2.gui.MenuListener;
import me.marco.core.v2.perms.PermissionManagerV2;
import me.marco.core.v2.portals.ModernPortalManager;
import me.marco.core.v2.selection.SelectionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreV2 extends JavaPlugin {
    private static CoreV2 instance;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private DatabaseManager databaseManager;
    private SelectionManager selectionManager;
    private PermissionManagerV2 permissionManager;
    private ModernPortalManager portalManager;
    private AdminManager adminManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        
        // Managers
        this.databaseManager = new DatabaseManager(this);
        this.selectionManager = new SelectionManager();
        this.permissionManager = new PermissionManagerV2(databaseManager);
        this.portalManager = new ModernPortalManager();
        this.adminManager = new AdminManager();

        // Comandos
        getCommand("core").setExecutor(new CoreCommand());
        getCommand("premium").setExecutor(new PremiumCommand());
        AdminCommands adminCommands = new AdminCommands(adminManager);
        getCommand("vanish").setExecutor(adminCommands);
        getCommand("freeze").setExecutor(adminCommands);
        getCommand("spectate").setExecutor(adminCommands);
        
        // Listeners
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new me.marco.core.v2.selection.SelectionListener(), this);
        getServer().getPluginManager().registerEvents(portalManager, this);
        getServer().getPluginManager().registerEvents(adminManager, this);
        
        getComponentLogger().info(miniMessage.deserialize("<gradient:#55ff55:#aaffaa>MC-MegaCore v2 (Modern) ha sido activado!</gradient>"));
    }

    @Override
    public void onDisable() {
        getComponentLogger().info(miniMessage.deserialize("<red>MC-Core v2 desactivado.</red>"));
    }

    public static CoreV2 getInstance() {
        return instance;
    }

    public MiniMessage getMiniMessage() {
        return miniMessage;
    }

    public PermissionManagerV2 getPermissionManager() {
        return permissionManager;
    }

    public SelectionManager getSelectionManager() {
        return selectionManager;
    }
    
    public void sendMessage(org.bukkit.entity.Player player, String message) {
        player.sendMessage(miniMessage.deserialize(message));
    }
}
