package me.goowen.kitpvp.Modules.Kits;

import me.goowen.kitpvp.Modules.Spawns.SpawnsManager;
import me.goowen.kitpvp.Modules.Spawns.SpawnsModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
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
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();

    /**
     * Alles wat te maken heeft met het klikken in de kits selector!
     * @param event
     */
    @EventHandler
    public void KitsSelectMenuClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory inventory = event.getClickedInventory();

        //Controleert of de speler een inventory open heeft.
        if (inventory == null)
        {
            return;
        }

        //Controleert of de speler de juiste inventory open heeft.
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Kies een Kit!"))
        {
            //Controleert of er wel op een item word gedrukt.
            if (item == null || !item.hasItemMeta())
            {
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);

            //Sluit de inventory en kiest een random spawnlocatie uit een lijst en teleporteert heb daarheen, daarna wordt de kit gegeven.
            player.closeInventory();
            String selectedkit = ChatColor.stripColor(item.getItemMeta().getDisplayName());
            player.teleport(spawnsManager.newSpawnlocation());
            databaseManager.getPlayerDBbyUUID(player).setLobbymode(false);
            kitsManager.giveKit(player, selectedkit);
        }
    }
}
