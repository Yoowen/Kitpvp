package me.goowen.kitpvp;

import lombok.Getter;
import lombok.Setter;
import me.goowen.kitpvp.Modules.Configs.ConfigModule;
import me.goowen.kitpvp.Modules.Utilities.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private long loadMS;
    private static @Getter @Setter Main instance;

    @Override
    public void onEnable() {
        setInstance(this);
        new ConfigModule();
        this.loadMS = System.currentTimeMillis();
        Log.INFO.send("Gestart met het laden van kitPVP V" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        // Plugin zou hierna volledig moeten aan gaan.

        //Plugin zou volledig aan moeten staan na deze message.
        Log.INFO.send("Kit pvp succesvol geladen. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);

    }


    public void registerCommand(String command, CommandExecutor c) {
        this.getCommand(command).setExecutor(c);

    }
}
