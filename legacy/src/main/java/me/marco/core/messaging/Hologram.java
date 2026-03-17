package me.marco.core.messaging;

import me.marco.core.utils.ChatUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Hologram {
    private final List<ArmorStand> lines = new ArrayList<>();
    private final Location location;

    public Hologram(Location location, List<String> textLines) {
        this.location = location;
        double yOffset = 0;
        for (int i = textLines.size() - 1; i >= 0; i--) {
            ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, yOffset, 0), EntityType.ARMOR_STAND);
            as.setVisible(false);
            as.setGravity(false);
            as.setCustomName(ChatUtil.color(textLines.get(i)));
            as.setCustomNameVisible(true);
            as.setMarker(true);
            lines.add(as);
            yOffset += 0.25;
        }
    }

    public void remove() {
        for (ArmorStand as : lines) {
            as.remove();
        }
    }
}
