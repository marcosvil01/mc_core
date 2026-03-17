package me.marco.core.v2;

import me.marco.core.v2.gui.MenuProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo jugadores pueden usar este comando.");
            return true;
        }
        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("menu")) {
                MenuProvider.openMainMenu(player);
                return true;
            }
        }

        CoreV2.getInstance().sendMessage(player, "<yellow>Uso: /core menu</yellow>");
        return true;
    }
}
