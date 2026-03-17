package me.marco.core.auth;

import me.marco.core.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AuthCommands implements CommandExecutor {
    private final AuthManager authManager;

    public AuthCommands(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("register")) {
            if (authManager.isRegistered(player.getUniqueId())) {
                player.sendMessage(ChatUtil.color("&cYou are already registered!"));
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatUtil.color("&cUsage: /register <password>"));
                return true;
            }
            authManager.register(player.getUniqueId(), args[0]);
            authManager.setAuthenticated(player.getUniqueId(), true);
            player.sendMessage(ChatUtil.color("&aSuccessfully registered and logged in!"));
        } else if (label.equalsIgnoreCase("login")) {
            if (authManager.isAuthenticated(player.getUniqueId())) {
                player.sendMessage(ChatUtil.color("&cYou are already logged in!"));
                return true;
            }
            if (!authManager.isRegistered(player.getUniqueId())) {
                player.sendMessage(ChatUtil.color("&cYou need to /register first!"));
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatUtil.color("&cUsage: /login <password>"));
                return true;
            }
            if (authManager.checkPassword(player.getUniqueId(), args[0])) {
                authManager.setAuthenticated(player.getUniqueId(), true);
                player.sendMessage(ChatUtil.color("&aSuccessfully logged in!"));
            } else {
                player.sendMessage(ChatUtil.color("&cIncorrect password!"));
            }
        } else if (label.equalsIgnoreCase("premium")) {
            boolean current = authManager.isPremium(player.getUniqueId());
            authManager.setPremium(player.getUniqueId(), !current);
            player.sendMessage(ChatUtil.color("&eModo Premium: " + (!current ? "&aACTIVADO" : "&cDESACTIVADO")));
            player.sendMessage(ChatUtil.color("&7(Si lo activas, no se te pedirá login la próxima vez)"));
        }
        return true;
    }
}
