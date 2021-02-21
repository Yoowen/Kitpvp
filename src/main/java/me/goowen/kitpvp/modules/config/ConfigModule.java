package me.goowen.kitpvp.modules.config;

import lombok.Getter;
import org.bukkit.ChatColor;

public class ConfigModule
{
    private @Getter ConfigManager spawnsConfig;
    private @Getter ConfigManager kitsConfig;
    public static @Getter ConfigModule configModule;

    /**
     * Maakt de Spawns.yml en kits.yml aan en geeft de instance van de classes!
     */
    public void ConfigModule()
    {
        configModule = this;
        this.spawnsConfig = new ConfigManager("spawns.yml");
        this.kitsConfig =  new ConfigManager("kits.yml");
        System.out.println(ChatColor.DARK_AQUA + "[ConfigModule] De module is succesvol geladen!");
    }
}
