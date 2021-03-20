package me.goowen.kitpvp.modules.config;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.ChatColor;

public class ConfigModule
{
    private @Getter Config config;
    private @Getter Config spawnsConfig;
    private @Getter Config kitsConfig;

    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Maakt de Spawns.yml en kits.yml aan en geeft de instance van de classes!
     */
    public ConfigModule()
    {
        this.spawnsConfig = new Config("spawns.yml");
        this.kitsConfig =  new Config("kits.yml");
        this.config = new Config("config.yml");
        plugin.getLog().info(ChatColor.DARK_AQUA + "[ConfigModule] De module is succesvol geladen!");
    }
}
