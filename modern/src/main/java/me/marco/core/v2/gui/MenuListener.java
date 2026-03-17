package me.marco.core.v2.gui;

import me.marco.core.v2.CoreV2;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        
        String title = CoreV2.getInstance().getMiniMessage().serialize(event.getView().title());
        if (!title.contains("Panel de Control v2")) return;
        
        event.setCancelled(true);
        
        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;
        
        Material type = item.getType();
        switch (type) {
            case FEATHER:
                CoreV2.getInstance().sendMessage(player, "<green>Doble Salto alternado!</green>");
                break;
            case SUGAR:
                CoreV2.getInstance().sendMessage(player, "<aqua>Velocidad alternada!</aqua>");
                break;
            case ENDER_PEARL:
                player.teleport(player.getWorld().getSpawnLocation());
                CoreV2.getInstance().sendMessage(player, "<light_purple>Teletransportado al spawn!</light_purple>");
                player.closeInventory();
                break;
            case ENDER_EYE:
                player.performCommand("vanish");
                player.closeInventory();
                break;
            case ICE:
                player.sendMessage(CoreV2.getInstance().getMiniMessage().deserialize("<yellow>Usa /freeze <jugador> en el chat.</yellow>"));
                player.closeInventory();
                break;
            case SPYGLASS:
                player.performCommand("spectate");
                player.closeInventory();
                break;
        }
    }
}
