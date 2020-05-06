package me.goowen.kitpvp.Modules.Configs;

import lombok.Getter;
import lombok.Setter;
import me.goowen.kitpvp.Main;

public class ConfigModule
{
    private static @Getter @Setter ConfigModule instance;

    public ConfigModule()
    {
        setInstance(this);
        setupConfig();
    }

    public void  setupConfig()
    {
        ConfigManager config = new ConfigManager(Main.getInstance(), "config.yml");
        config.createConfigPath("Datbase.dbname", "name");
        config.createConfigPath("Datbase.user", "user");
        config.createConfigPath("Datbase.wachtwoord", "wachtwoord");
        config.createConfigPath("Datbase.connection", "connection");
        config.save(config);
    }
}
