package me.goowen.kitpvp.Modules.database;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class DatabaseModule
{
    private static @Getter DatabaseModule databaseModule;
    public static @Getter
    AcountManager databaseManager;

    private static TaskChainFactory taskChainFactory;
    private Kitpvp plugin = Kitpvp.getInstance();

    public void DatabaseModule()
    {
        databaseModule = this;
        databaseManager = new AcountManager();

        MongoConnect();

        taskChainFactory = BukkitTaskChainFactory.create(plugin);
        System.out.println(ChatColor.DARK_AQUA + "[DatabaseModule] De module is succesvol geladen!");
    }

    public void MongoConnect()
    {
        databaseManager.playerDBhashMap = new HashMap<>();
        String uri = "mongodb+srv://goowen:admin@cluster1.mpyu5.mongodb.net/test";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Kitpvp");
        databaseManager.setPlayerDBcollection(mongoDatabase.getCollection("playerDB"));

        System.out.println(ChatColor.GREEN + "[Kitpvp] Database has been connected!");
    }

    public static <T> TaskChain<T> newChain()
    {
        return taskChainFactory.newChain();
    }
}
