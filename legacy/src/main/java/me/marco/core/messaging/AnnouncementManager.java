package me.marco.core.messaging;

import me.marco.core.McCore;
import me.marco.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AnnouncementManager {
    private final List<String> announcements = Arrays.asList(
            "&eRemember to use &b/help &efor commands!",
            "&aWelcome to our server! Have fun!",
            "&cBe respectful to other players.",
            "&6Join our Discord for updates!"
    );
    private final Random random = new Random();

    public AnnouncementManager(McCore plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                String message = announcements.get(random.nextInt(announcements.size()));
                Bukkit.broadcastMessage(ChatUtil.color("&8[&bAnnounce&8] " + message));
            }
        }.runTaskTimer(plugin, 0, 20 * 60 * 5); // Every 5 minutes
    }
}
