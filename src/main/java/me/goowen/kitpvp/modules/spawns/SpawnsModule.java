package me.goowen.kitpvp.modules.spawns;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class SpawnsModule
{
    public static @Getter SpawnsManager spawnsManager;
    private Kitpvp plugin = Kitpvp.getInstance();


    /**
     * Maakt instances aan voor de spawnsModule en de spawnsManager en registeert de SpawnsCommands en SpawnsListener
     */
    public SpawnsModule()
    {
        spawnsManager = new SpawnsManager();
        Bukkit.getPluginManager().registerEvents(new SpawnListener(), plugin);
        plugin.getCommand("spawneditmode").setExecutor(new SpawnsCommand());

        plugin.getLog().info(ChatColor.DARK_AQUA + "[SpawnsModule] De module is succesvol geladen!");
    }
}
