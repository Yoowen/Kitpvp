package me.goowen.kitpvp.modules.config;

import lombok.Getter;
import org.bukkit.ChatColor;

public class ConfigModule
{
    private @Getter
    Config config;
    private @Getter
    Config spawnsConfig;
    private @Getter
    Config kitsConfig;
    public static @Getter ConfigModule configModule;

    /**
     * Maakt de Spawns.yml en kits.yml aan en geeft de instance van de classes!
     */
    public ConfigModule()
    {
        configModule = this;
        this.spawnsConfig = new Config("spawns.yml");
        this.kitsConfig =  new Config("kits.yml");
        this.config = new Config("config.yml");
        System.out.println(ChatColor.DARK_AQUA + "[ConfigModule] De module is succesvol geladen!");
    }
}
