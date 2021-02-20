package me.goowen.kitpvp.Modules.Spawns;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class SpawnsModule
{
    public static @Getter SpawnsModule spawnsModule;
    public static @Getter SpawnsManager spawnsManager;
    private Kitpvp plugin = Kitpvp.getInstance();

    public void SpawnsModule()
    {
        spawnsModule = this;
        spawnsManager = new SpawnsManager();
        Bukkit.getPluginManager().registerEvents(new SpawnListener(), plugin);
        plugin.getCommand("spawneditmode").setExecutor(new SpawnsCommand());

        System.out.println(ChatColor.BLUE + "[SpawnsModule] De module is succesvol geladen!");
    }
}
