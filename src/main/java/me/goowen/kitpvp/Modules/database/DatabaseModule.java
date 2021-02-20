package me.goowen.kitpvp.Modules.database;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import org.bukkit.ChatColor;

public class DatabaseModule
{
    private static @Getter DatabaseModule databaseModule;
    public static @Getter DatabaseManager databaseManager;

    private static TaskChainFactory taskChainFactory;
    private Kitpvp plugin = Kitpvp.getInstance();

    public void DatabaseModule()
    {
        databaseModule = this;
        databaseManager = new DatabaseManager();

        databaseManager.MongoConnect();

        taskChainFactory = BukkitTaskChainFactory.create(plugin);
        System.out.println(ChatColor.BLUE + "[DatabaseModule] De module is succesvol geladen!");
    }

    public static <T> TaskChain<T> newChain()
    {
        return taskChainFactory.newChain();
    }
}
