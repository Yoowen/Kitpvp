package me.goowen.kitpvp.modules.kits;

import me.goowen.kitpvp.modules.spawns.SpawnsManager;
import me.goowen.kitpvp.modules.spawns.SpawnsModule;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
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
    private AcountManager acountManager = DatabaseModule.getAcountManager();

    /**
     * Alles wat te maken heeft met het klikken in de kits selector!
     * @param event
     */
    @EventHandler
    public void kitsSelectMenuClick(InventoryClickEvent event)
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
            try
            {
                String selectedkit = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                player.teleport(spawnsManager.newSpawnlocation());
                acountManager.getPlayerDBbyUUID(player).setLobbymode(false);
                kitsManager.giveKit(player, selectedkit);
            }
            catch (NullPointerException expetion)
            {
                player.sendMessage(ChatColor.RED + "Something Went Wrong, Maybe add some kits or spawnlocations?");
            }
        }
    }
}
