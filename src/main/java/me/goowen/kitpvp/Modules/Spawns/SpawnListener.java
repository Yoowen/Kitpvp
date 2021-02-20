package me.goowen.kitpvp.Modules.Spawns;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnListener implements Listener
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();
    private SpawnsManager spawnsManager = SpawnsModule.getSpawnsManager();
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     *  Event voor het het plaatsen van spawnpunten
     * @param event
     */
    @EventHandler
    public void PlaceNewSpawn(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        PlayerDB playerDB = databaseManager.getPlayerDBbyUUID(player);
        if (playerDB.isSpawnEditmode() && player.getGameMode().equals(GameMode.CREATIVE))
        {
            if (player.getInventory().getItemInMainHand().equals(spawnsManager.SpawnEditor()))
            {
                if (!spawnsManager.isSpawn(event.getBlock().getLocation()))
                {
                    spawnsManager.addlocation(event.getBlock().getLocation());
                }

                event.getBlock().setType(Material.AIR);
                Bukkit.getScheduler().runTaskLater(plugin, () -> spawnsManager.ShowSpawns(event.getPlayer()), 3);
            }
        }
    }

    /**
     * The event voor het verwijderen van spawnpunten
     * @param event
     */
    @EventHandler
    public void RemoveOldSpawn(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        PlayerDB playerDB = databaseManager.getPlayerDBbyUUID(player);
        if (playerDB.isSpawnEditmode() && player.getGameMode().equals(GameMode.CREATIVE))
        {
            if (event.getBlock().getType().equals(Material.AIR))
            {
                spawnsManager.removelocation(event.getBlock().getLocation());
                event.getBlock().setType(Material.AIR);
                Bukkit.getScheduler().runTaskLater(plugin, () -> spawnsManager.ShowSpawns(event.getPlayer()), 3);
            }
        }
    }
}
