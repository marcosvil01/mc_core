package me.marco.core.spawn;

import me.marco.core.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommands implements CommandExecutor {
    private final SpawnManager spawnManager;

    public SpawnCommands(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("setspawn")) {
            if (!player.hasPermission("core.admin")) {
                player.sendMessage(ChatUtil.color("&cNo permission!"));
                return true;
            }
            spawnManager.setSpawn(player.getLocation());
            player.sendMessage(ChatUtil.color("&aSpawn protected area set!"));
        } else if (label.equalsIgnoreCase("spawn")) {
            player.teleport(spawnManager.getSpawn());
            player.sendMessage(ChatUtil.color("&aTeleported to spawn!"));
        }
        return true;
    }
}
