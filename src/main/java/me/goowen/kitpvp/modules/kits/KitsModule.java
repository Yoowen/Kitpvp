package me.goowen.kitpvp.modules.kits;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class KitsModule
{
    private Kitpvp plugin = Kitpvp.getInstance();
    public static @Getter KitsManager kitsManager;

    /**
     * Maakt een instance aan voor deze class, en registeert het createkit command en de kits listener
     */
    public KitsModule()
    {
        kitsManager = new KitsManager();
        plugin.getCommand("kit").setExecutor(new KitsCommand());
        plugin.getCommand("kit").setTabCompleter(new KitsCommandTabCompleter());
        Bukkit.getPluginManager().registerEvents(new KitsListener(), plugin);
        plugin.getLog().info(ChatColor.DARK_AQUA + "[KitsModule] De module is succesvol geladen!");
    }
}
