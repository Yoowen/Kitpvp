package me.goowen.kitpvp.Modules.Kits;

import me.goowen.kitpvp.Modules.Spawns.SpawnsManager;
import me.goowen.kitpvp.Modules.Spawns.SpawnsModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitsListener implements Listener
{
    private KitsManager kitsManager = KitsModule.getKitsManager();
    private SpawnsManager spawnsManager = SpawnsModule.getSpawnsManager();
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();

    @EventHandler
    public void KitsSelectMenuClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) {
            return;
        }
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Kies een Kit!"))
        {
            if (item == null || !item.hasItemMeta())
            {
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);
            player.closeInventory();
            String selectedkit = ChatColor.stripColor(item.getItemMeta().getDisplayName());
            player.teleport(spawnsManager.newSpawnlocation());
            databaseManager.getPlayerDBbyUUID(player).setLobbymode(false);
            kitsManager.giveKit(player, selectedkit);
        }
    }
}
