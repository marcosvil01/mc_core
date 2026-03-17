package me.marco.core.v2.gui;

import me.marco.core.v2.CoreV2;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;
import java.util.List;

public class MenuProvider {
    
    public static void openMainMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, CoreV2.getInstance().getMiniMessage().deserialize("<dark_gray>Panel de Control v2</dark_gray>"));
        
        // Botón de Doble Salto
        inv.setItem(11, createItem(Material.FEATHER, "<yellow>Habilidad: Doble Salto</yellow>", 
                List.of("<gray>Click para alternar</gray>", "<green>Estado: ACTIVADO</green>")));
        
        // Botón de Velocidad
        inv.setItem(13, createItem(Material.SUGAR, "<aqua>Habilidad: Velocidad</aqua>", 
                List.of("<gray>Click para alternar</gray>", "<green>Estado: ACTIVADO</green>")));
        
        // Botón de Spawn
        inv.setItem(15, createItem(Material.ENDER_PEARL, "<light_purple>Teletransporte: Spawn</light_purple>", 
                List.of("<gray>Click para ir al spawn</gray>")));
        
        // Admin Tools
        ItemStack vanish = createItem(Material.ENDER_EYE, "<red><b>MODO VANISH</b></red>", List.of("<gray>Hazte invisible para otros.</gray>"));
        ItemStack freeze = createItem(Material.ICE, "<aqua><b>CONGELAR JUGADORES</b></aqua>", List.of("<gray>Usa /freeze <nombre> para bloquear.</gray>"));
        ItemStack spectate = createItem(Material.SPYGLASS, "<yellow><b>MODO ESPECTADOR</b></yellow>", List.of("<gray>Cambia rápido a espectador.</gray>"));

        inv.setItem(20, vanish);
        inv.setItem(22, freeze);
        inv.setItem(24, spectate);

        player.openInventory(inv);
    }
    
    private static ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(CoreV2.getInstance().getMiniMessage().deserialize("<!italic>" + name));
        meta.lore(lore.stream()
                .map(line -> CoreV2.getInstance().getMiniMessage().deserialize("<!italic>" + line))
                .collect(Collectors.toList()));
        item.setItemMeta(meta);
        return item;
    }
}
