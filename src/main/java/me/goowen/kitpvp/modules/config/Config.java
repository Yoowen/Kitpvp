package me.goowen.kitpvp.modules.config;

import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config
{
    private File config;
    private YamlConfiguration yamlConfiguration;
    private String name;
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Maakt Deze class aan en roept de class aan een config te creeren.
     * @param name De naam van de config
     */
    public Config(String name)
    {
        this.name = name;
        this.createCustomConfig();
        try
        {
            this.yamlConfiguration.save(config);
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

        this.yamlConfiguration = new YamlConfiguration();

        try
        {
            yamlConfiguration.load(config);
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
    public YamlConfiguration getConfigConfiguration()
    {
        return yamlConfiguration;
    }

    /**
     * Slaat de Config Async op
     */
    public void saveAsync(Config configManager)
    {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, () ->
        {
            try
            {
                configManager.getConfigConfiguration().save(configManager.getConfig());
                createCustomConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}


