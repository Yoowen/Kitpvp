package me.goowen.kitpvp;

import lombok.Getter;
import me.goowen.kitpvp.modules.config.ConfigModule;
import me.goowen.kitpvp.modules.eventlisteners.EventListenersModule;
import me.goowen.kitpvp.modules.kits.KitsModule;
import me.goowen.kitpvp.modules.lobby.LobbyModule;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.scoreboard.ScoreboardModule;
import me.goowen.kitpvp.modules.spawns.SpawnsModule;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Kitpvp extends JavaPlugin {

    public static @Getter Kitpvp instance;

    public static @Getter ConfigModule configModule;
    public static @Getter DatabaseModule databaseModule;
    public static @Getter ScoreboardModule scoreboardModule;
    public static @Getter LobbyModule lobbyModule;
    public static @Getter SpawnsModule spawnsModule;
    public static @Getter KitsModule kitsModule;
    public static @Getter EventListenersModule eventListenersModule;

    public final @Getter Logger log = this.getLogger();
    private long loadMS;

    @Override
    public void onEnable()
    {
        instance = this;
        loadMS = System.currentTimeMillis();
        log.info(ChatColor.BLUE + "[Plugin] Gestart met het laden van kitPVP V" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));

        configModule = new ConfigModule();
        databaseModule = new DatabaseModule();
        scoreboardModule = new ScoreboardModule();
        spawnsModule = new SpawnsModule();
        kitsModule = new KitsModule();
        lobbyModule = new LobbyModule();
        eventListenersModule = new EventListenersModule();

        log.info(ChatColor.BLUE + "[Plugin] Kit pvp succesvol geladen. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    @Override
    public void onDisable()
    {
        loadMS = System.currentTimeMillis();
        log.info(ChatColor.BLUE + "[plugin] Kit pvp succesvol Uitgezet. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }
}