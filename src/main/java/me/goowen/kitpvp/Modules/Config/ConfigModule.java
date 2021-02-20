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
        this.spawnsConfig = new ConfigManager("spawns.yml");
        this.kitsConfig =  new ConfigManager("kitsconfig.yml");
        System.out.println(ChatColor.DARK_AQUA + "[ConfigModule] De module is succesvol geladen!");
    }
}
