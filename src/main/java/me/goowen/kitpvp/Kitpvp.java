package me.goowen.kitpvp;

import lombok.Getter;
import me.goowen.kitpvp.modules.config.ConfigModule;
import me.goowen.kitpvp.modules.eventlisteners.EventListenersModule;
import me.goowen.kitpvp.modules.kits.KitsModule;
import me.goowen.kitpvp.modules.lobby.LobbyModule;
import me.goowen.kitpvp.modules.spawns.SpawnsModule;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.scoreboard.ScoreboardModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kitpvp extends JavaPlugin {
    private long loadMS;
    private static @Getter Kitpvp instance;

    @Override
    public void onEnable() {
        instance = this;
        loadMS = System.currentTimeMillis();
        System.out.println("Gestart met het laden van kitPVP V" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        new ConfigModule();
        new DatabaseModule();
        new ScoreboardModule();
        new SpawnsModule();
        new KitsModule();
        new LobbyModule();
        new EventListenersModule();
        System.out.println("Kit pvp succesvol geladen. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    @Override
    public void onDisable() {
        loadMS = System.currentTimeMillis();
        System.out.println("Kit pvp succesvol Uitgezet. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }
}