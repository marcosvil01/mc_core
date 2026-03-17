package me.marco.core.v2.admin;

import me.marco.core.v2.CoreV2;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AdminManager implements Listener {
    private final Set<UUID> vanished = new HashSet<>();
    private final Set<UUID> frozen = new HashSet<>();

    public void toggleVanish(Player player) {
        if (vanished.contains(player.getUniqueId())) {
            vanished.remove(player.getUniqueId());
            Bukkit.getOnlinePlayers().forEach(p -> p.showPlayer(CoreV2.getInstance(), player));
            CoreV2.getInstance().sendMessage(player, "<green>Vanish desactivado.</green>");
        } else {
            vanished.add(player.getUniqueId());
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (!p.hasPermission("core.admin")) {
                    p.hidePlayer(CoreV2.getInstance(), player);
                }
            });
            CoreV2.getInstance().sendMessage(player, "<red>Vanish activado.</red>");
        }
    }

    public void toggleFreeze(Player target, Player admin) {
        if (frozen.contains(target.getUniqueId())) {
            frozen.remove(target.getUniqueId());
            CoreV2.getInstance().sendMessage(admin, "<green>Has descongelado a " + target.getName() + ".</green>");
            CoreV2.getInstance().sendMessage(target, "<green>Has sido descongelado.</green>");
        } else {
            frozen.add(target.getUniqueId());
            CoreV2.getInstance().sendMessage(admin, "<red>Has congelado a " + target.getName() + ".</red>");
            CoreV2.getInstance().sendMessage(target, "<red>HAS SIDO CONGELADO PARA REVISIÓN.</red>");
        }
    }

    public void toggleSpectate(Player player) {
        if (player.getGameMode() == GameMode.SPECTATOR) {
            player.setGameMode(GameMode.SURVIVAL);
            CoreV2.getInstance().sendMessage(player, "<yellow>Modo Espectador: OFF</yellow>");
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            CoreV2.getInstance().sendMessage(player, "<yellow>Modo Espectador: ON</yellow>");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (frozen.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    public boolean isVanished(UUID uuid) { return vanished.contains(uuid); }
    public boolean isFrozen(UUID uuid) { return frozen.contains(uuid); }
}
