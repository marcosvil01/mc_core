package me.marco.core.commands;

import me.marco.core.McCore;
import me.marco.core.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class CommandFilter implements Listener {
    private final List<String> allowedCommands = Arrays.asList(
            "/login", "/register", "/spawn", "/help", "/me", "/msg", "/tell", "/premium", "/rank"
    );

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (McCore.getInstance().getPermissionManager().hasPermission(player, "core.admin")) return;

        String message = event.getMessage().toLowerCase().split(" ")[0];
        if (!allowedCommands.contains(message)) {
            event.setCancelled(true);
            player.sendMessage(ChatUtil.color("&cEse comando no está permitido para tu rango!"));
        }
    }
}
