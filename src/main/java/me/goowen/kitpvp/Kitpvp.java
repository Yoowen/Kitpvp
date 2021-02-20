package me.goowen.kitpvp;

import lombok.Getter;
import me.goowen.kitpvp.Modules.Config.ConfigModule;
import me.goowen.kitpvp.Modules.EventListeners.EventListenersModule;
import me.goowen.kitpvp.Modules.Kits.KitsModule;
import me.goowen.kitpvp.Modules.Lobby.LobbyModule;
import me.goowen.kitpvp.Modules.Spawns.SpawnsModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.scoreboard.ScoreboardModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kitpvp extends JavaPlugin {
    private long loadMS;
    private static @Getter Kitpvp instance;

    @Override
    public void onEnable() {
        instance = this;
        loadMS = System.currentTimeMillis();
        System.out.println("Gestart met het laden van kitPVP V" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        new DatabaseModule().DatabaseModule();
        new ScoreboardModule().ScoreboardModule();
        new ConfigModule().ConfigModule();
        new SpawnsModule().SpawnsModule();
        new KitsModule().KitsModule();
        new LobbyModule().LobbyModule();
        new EventListenersModule().EventListenersModule();
        System.out.println("Kit pvp succesvol geladen. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    @Override
    public void onDisable() {
        loadMS = System.currentTimeMillis();
        System.out.println("Kit pvp succesvol Uitgezet. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }
}