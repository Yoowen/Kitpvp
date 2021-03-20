package me.goowen.kitpvp.modules.eventlisteners;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.eventlisteners.listeners.PlayerDeathListener;
import me.goowen.kitpvp.modules.eventlisteners.listeners.PlayerDropEvent;
import me.goowen.kitpvp.modules.eventlisteners.listeners.PlayerLoginListener;
import me.goowen.kitpvp.modules.eventlisteners.listeners.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class EventListenersModule
{
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Maakt een instance aan van deze class en registeert de volgende events:
     * - Playerlogin
     * - PlayerQuit
     * - PlayerDeath
     * - PlayerDrop
     */
    public EventListenersModule()
    {
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDropEvent(), plugin);
        plugin.getLog().info(ChatColor.DARK_AQUA + "[eventListenerModule] De module is succesvol geladen!");
    }
}
