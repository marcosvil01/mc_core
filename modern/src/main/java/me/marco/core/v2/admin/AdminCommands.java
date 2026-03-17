package me.marco.core.v2.admin;

import me.marco.core.v2.CoreV2;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommands implements CommandExecutor {
    private final AdminManager adminManager;

    public AdminCommands(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("core.admin")) {
            CoreV2.getInstance().sendMessage(player, "<red>Sin permisos.</red>");
            return true;
        }

        switch (label.toLowerCase()) {
            case "vanish":
                adminManager.toggleVanish(player);
                break;
            case "freeze":
                if (args.length == 0) {
                    CoreV2.getInstance().sendMessage(player, "<yellow>Uso: /freeze <jugador></yellow>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    CoreV2.getInstance().sendMessage(player, "<red>Jugador no encontrado.</red>");
                    return true;
                }
                adminManager.toggleFreeze(target, player);
                break;
            case "spectate":
                adminManager.toggleSpectate(player);
                break;
        }

        return true;
    }
}
