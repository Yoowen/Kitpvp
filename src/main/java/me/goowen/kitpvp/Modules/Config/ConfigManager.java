package me.goowen.kitpvp.Modules.Config;

import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigManager
{
    private File config;
    private FileConfiguration configConfiguration;
    private Kitpvp plugin;
    private String name;

    //Call upon this class
    public ConfigManager(Kitpvp plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.createCustomConfig();
        try {
            this.configConfiguration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Creates a new config
    private void createCustomConfig() {
        this.config = new File(plugin.getDataFolder(), this.name);
        if (!config.exists()) {
            if (plugin.getResource(this.name) == null) {
                try {
                    config.getParentFile().mkdirs();
                    config.createNewFile();
                    //plugin.saveResource(this.name, false);
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

        try {
            configConfiguration.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    //Returns config
    public File getConfig() {
        return config;
    }

    public FileConfiguration getConfigConfiguration() {
        return configConfiguration;
    }

    //Config saving
    public void saveAsync(ConfigManager c) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                c.getConfigConfiguration().save(c.getConfig());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void save(ConfigManager c) {
        try {
            c.getConfigConfiguration().save(c.getConfig());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Path creator
    public void createConfigPath(String path, boolean value) {

        if (!this.getConfigConfiguration().contains(path)) {
            this.getConfigConfiguration().set(path, value);

        }
    }

    public void createConfigPath(String path, int value) {

        if (!this.getConfigConfiguration().contains(path)) {
            this.getConfigConfiguration().set(path, value);

        }
    }

    public void createConfigPath(String path, ArrayList value) {

        if (!this.getConfigConfiguration().contains(path)) {
            this.getConfigConfiguration().set(path, value);

        }
    }

    public void createConfigPath(String path, String value) {

        if (!this.getConfigConfiguration().contains(path)) {
            this.getConfigConfiguration().set(path, value);

        }
    }

    public void createConfigPath(String path, ItemStack[] value)
    {
        if (!this.getConfigConfiguration().contains(path)) {
            this.getConfigConfiguration().set(path, value);

        }
    }

    public void createConfigPath(String path, ItemStack value)
    {
        if (!this.getConfigConfiguration().contains(path)) {
            this.getConfigConfiguration().set(path, value);

        }
    }
}


