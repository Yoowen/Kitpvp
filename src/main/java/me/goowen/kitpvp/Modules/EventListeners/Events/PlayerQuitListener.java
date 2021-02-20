package me.goowen.kitpvp.Modules.EventListeners.Events;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();
    private Kitpvp plugin = Kitpvp.getInstance();

    @EventHandler
    public void OnQuit(PlayerQuitEvent event)
    {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> databaseManager.save(event.getPlayer()));
        Bukkit.getScheduler().runTaskLater(plugin, () -> databaseManager.playerDBhashMap.remove(event.getPlayer().getUniqueId()), 7);
    }

    @EventHandler
    public void OnKick(PlayerKickEvent event)
    {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> databaseManager.save(event.getPlayer()));
        Bukkit.getScheduler().runTaskLater(plugin, () -> databaseManager.playerDBhashMap.remove(event.getPlayer().getUniqueId()), 7);
    }
}
