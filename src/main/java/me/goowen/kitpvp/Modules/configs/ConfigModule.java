package me.goowen.kitpvp.Modules.configs;

import lombok.Getter;
import lombok.Setter;

public class ConfigModule
{
    private static @Getter @Setter ConfigModule instance;

    public ConfigModule() {
        setInstance(this);
        initConfig();
    }
}
