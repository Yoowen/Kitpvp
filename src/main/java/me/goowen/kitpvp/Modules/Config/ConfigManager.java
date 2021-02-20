package me.goowen.kitpvp.Modules.Config;

import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class ConfigManager
{
    private File config;
    private FileConfiguration configConfiguration;
    private String name;
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Maakt Deze class aan en roept de class aan een config te creeren.
     * @param name De naam van de config
     */
    public ConfigManager(String name)
    {
        this.name = name;
        this.createCustomConfig();
        try
        {
            this.configConfiguration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Maakt een nieuwe config aan in de vorm van een YML en
     * wanneer hij al bestaat slaat hij hem op in deze class.
     */
    private void createCustomConfig()
    {
        this.config = new File(plugin.getDataFolder(), this.name);
        //Als de Config niet bestaan word hij opniew aangemaakt
        if (!config.exists())
        {
            if (plugin.getResource(this.name) == null) {
                try {
                    config.getParentFile().mkdirs();
                    config.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (plugin.getResource(this.name) != null) {
                config.getParentFile().mkdirs();
                plugin.saveResource(this.name, false);
            } else {
                return;
            }
        }

        this.configConfiguration = new YamlConfiguration();

        try
        {
            configConfiguration.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returnt de config die is aangemaakt.
     * @return config
     */
    public File getConfig()
    {
        return config;
    }

    /**
     * Returnt de configuratie van de yml file.
     * @return
     */
    public FileConfiguration getConfigConfiguration()
    {
        return configConfiguration;
    }

    /**
     * Slaat de Config Async op
     */
    public void saveAsync()
    {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, () ->
        {
            try
            {
                this.getConfigConfiguration().save(this.getConfig());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Creert een Pad en zet een value in de vorm van een ItemStacl[] er op.
     * @param path Het pad waar de value heen moet.
     * @param value De value die op het pad moet worden gezet.
     */
    public void createConfigPath(String path, ItemStack[] value)
    {
        if (!this.getConfigConfiguration().contains(path))
        {
            this.getConfigConfiguration().set(path, value);

        }
    }
}


