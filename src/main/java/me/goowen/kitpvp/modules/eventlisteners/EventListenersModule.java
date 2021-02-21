package me.goowen.kitpvp.modules.eventlisteners;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.eventlisteners.events.PlayerDeathListener;
import me.goowen.kitpvp.modules.eventlisteners.events.PlayerDropEvent;
import me.goowen.kitpvp.modules.eventlisteners.events.PlayerLoginListener;
import me.goowen.kitpvp.modules.eventlisteners.events.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class EventListenersModule
{
    @Getter
    public static EventListenersModule eventListenersModule;
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Maakt een instance aan van deze class en registeert de volgende events:
     * - Playerlogin
     * - PlayerQuit
     * - PlayerDeath
     * - PlayerDrop
     */
    public void EventListenersModule()
    {
        eventListenersModule = this;
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDropEvent(), plugin);
        System.out.println(ChatColor.DARK_AQUA + "[eventListenerModule] De module is succesvol geladen!");
    }
}
