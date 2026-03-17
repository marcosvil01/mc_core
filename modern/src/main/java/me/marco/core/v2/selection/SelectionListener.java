package me.marco.core.v2.selection;

import me.marco.core.v2.CoreV2;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectionListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null || event.getItem().getType() != Material.BLAZE_ROD) return;
        if (!player.hasPermission("core.admin")) return;

        event.setCancelled(true);
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            CoreV2.getInstance().getSelectionManager().setPos1(player, event.getClickedBlock().getLocation());
            CoreV2.getInstance().sendMessage(player, "<green>Posición 1 establecida!</green>");
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            CoreV2.getInstance().getSelectionManager().setPos2(player, event.getClickedBlock().getLocation());
            CoreV2.getInstance().sendMessage(player, "<green>Posición 2 establecida!</green>");
        }
    }
}
