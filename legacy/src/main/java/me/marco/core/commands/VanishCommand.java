package me.marco.core.commands;

import me.marco.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {
    private final Set<UUID> vanished = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("core.admin")) {
            player.sendMessage(ChatUtil.color("&cNo tienes permiso."));
            return true;
        }

        if (vanished.contains(player.getUniqueId())) {
            vanished.remove(player.getUniqueId());
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(player);
            }
            player.sendMessage(ChatUtil.color("&eAhora eres &aVISIBLE&e."));
        } else {
            vanished.add(player.getUniqueId());
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.hasPermission("core.admin")) {
                    online.hidePlayer(player);
                }
            }
            player.sendMessage(ChatUtil.color("&eAhora eres &cINVISIBLE &7(Vanish)."));
        }
        return true;
    }
}
