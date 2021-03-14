package me.goowen.kitpvp.modules.eventlisteners.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropEvent implements Listener
{
    /**
     * Voorkomt de speler items te laten dropen zolang hij of zij in een andere gamemode zit dan Creative.
     * @param event
     */
    @EventHandler
    public void playerDropEvent(PlayerDropItemEvent event)
    {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE)
        {
            event.setCancelled(true);
        }
    }
}
