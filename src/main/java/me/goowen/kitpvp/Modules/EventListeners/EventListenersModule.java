package me.goowen.kitpvp.Modules.EventListeners;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.EventListeners.Events.PlayerDeathListener;
import me.goowen.kitpvp.Modules.EventListeners.Events.PlayerDropEvent;
import me.goowen.kitpvp.Modules.EventListeners.Events.PlayerLoginListener;
import me.goowen.kitpvp.Modules.EventListeners.Events.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class EventListenersModule
{
    @Getter
    public static EventListenersModule eventListenersModule;
    private Kitpvp plugin = Kitpvp.getInstance();

    public void EventListenersModule()
    {
        eventListenersModule = this;
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDropEvent(), plugin);
        System.out.println(ChatColor.BLUE + "[eventListenerModule] De module is succesvol geladen!");
    }
}
