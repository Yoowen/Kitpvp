package me.goowen.kitpvp;

import me.goowen.kitpvp.Modules.database.DatabaseModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kitpvp extends JavaPlugin {
    private long loadMS;
    private static Kitpvp kitpvp;

    @Override
    public void onEnable() {
        kitpvp = this;
        this.loadMS = System.currentTimeMillis();
        System.out.println("Gestart met het laden van kitPVP V" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        new DatabaseModule().DatabaseModule();
        System.out.println("Kit pvp succesvol geladen. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    @Override
    public void onDisable() {
        this.loadMS = System.currentTimeMillis();
        System.out.println("Kit pvp succesvol Uitgezet. Dit prosess duurde " + (System.currentTimeMillis() - loadMS) + " ms!");
    }

    public static Kitpvp getInstance()
    {
        return kitpvp;
    }
}
