package me.goowen.kitpvp.Modules.Config;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.ChatColor;

public class ConfigModule
{
    private Kitpvp plugin = Kitpvp.getInstance();
    private @Getter ConfigManager spawnsConfig;
    private @Getter ConfigManager kitsConfig;
    public static @Getter ConfigModule configModule;

    public void ConfigModule()
    {
        configModule = this;
        this.spawnsConfig = new ConfigManager(plugin, "spawns.yml");
        this.kitsConfig =  new ConfigManager(plugin, "kitsconfig.yml");
        System.out.println(ChatColor.BLUE + "[ConfigModule] De module is succesvol geladen!");
    }
}
