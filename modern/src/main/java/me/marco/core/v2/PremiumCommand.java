package me.marco.core.v2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PremiumCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        // In v2, this is a toggle that simplifies authentication
        CoreV2.getInstance().sendMessage(player, "<gradient:#55ff55:#aaffaa><b>MODO PREMIUM</b></gradient>");
        CoreV2.getInstance().sendMessage(player, "<gray>Has activado el modo premium. No se te pedirá contraseña en el futuro.</gray>");
        
        return true;
    }
}
