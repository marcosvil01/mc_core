package me.marco.core.auth;

import me.marco.core.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AuthListener implements Listener {
    private final AuthManager authManager;

    public AuthListener(AuthManager authManager) {
        this.authManager = authManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        if (authManager.isPremium(player.getUniqueId())) {
            authManager.setAuthenticated(player.getUniqueId(), true);
            player.sendMessage(ChatUtil.color("&aWelcome back! (Modo Premium detectado, login saltado)"));
            return;
        }

        authManager.setAuthenticated(player.getUniqueId(), false);
        if (authManager.isRegistered(player.getUniqueId())) {
            player.sendMessage(ChatUtil.color("&ePlease log in using /login <password>"));
        } else {
            player.sendMessage(ChatUtil.color("&ePlease register using /register <password>"));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!authManager.isAuthenticated(event.getPlayer().getUniqueId())) {
            event.setTo(event.getFrom());
            event.getPlayer().sendMessage(ChatUtil.color("&cYou must log in to move!"));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!authManager.isAuthenticated(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatUtil.color("&cYou must log in to chat!"));
        }
    }
}
