package me.marco.core.perms;

import me.marco.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RankCommand implements CommandExecutor {
    private final PermissionManager permissionManager;

    public RankCommand(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.admin")) {
            sender.sendMessage(ChatUtil.color("&cNo tienes permiso para gestionar rangos."));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatUtil.color("&eUso: /rank <jugador> <MEMBER|VIP|ADMIN>"));
            return true;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        String rank = args[1].toUpperCase();

        if (!rank.equals("MEMBER") && !rank.equals("VIP") && !rank.equals("ADMIN")) {
            sender.sendMessage(ChatUtil.color("&cRango inválido. Usa MEMBER, VIP o ADMIN."));
            return true;
        }

        permissionManager.setRank(target.getUniqueId(), rank);
        sender.sendMessage(ChatUtil.color("&aHas establecido el rango de &e" + args[0] + " &aa &e" + rank));
        
        if (target.isOnline()) {
            target.getPlayer().sendMessage(ChatUtil.color("&aTu rango ha sido actualizado a: &6" + rank));
        }

        return true;
    }
}
