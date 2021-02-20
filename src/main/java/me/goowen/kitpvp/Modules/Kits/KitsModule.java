package me.goowen.kitpvp.Modules.Kits;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class KitsModule
{
    private Kitpvp plugin = Kitpvp.getInstance();
    public static @Getter KitsModule kitsModule;
    public static @Getter KitsManager kitsManager;

    public void KitsModule()
    {
        kitsModule = this;
        kitsManager = new KitsManager();
        plugin.getCommand("createkit").setExecutor(new KitsCommand());
        Bukkit.getPluginManager().registerEvents(new KitsListener(), plugin);
        System.out.println(ChatColor.DARK_AQUA + "[ConfigModule] De module is succesvol geladen!");
    }
}
