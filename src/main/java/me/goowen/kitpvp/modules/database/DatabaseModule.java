package me.goowen.kitpvp.modules.database;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.config.ConfigModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import org.bson.Document;
import org.bukkit.ChatColor;

public class DatabaseModule
{
    private static @Getter DatabaseModule databaseModule;
    public static @Getter AcountManager databaseManager;
    public static @Getter MongoCollection<Document> playerDBcollection;

    private static TaskChainFactory taskChainFactory;
    private Kitpvp plugin = Kitpvp.getInstance();
    private ConfigModule configModule = ConfigModule.getConfigModule();

    /**
     * Maakt de instances aan voor de databasemodule en databasemanager en zet taskchainfactory op!
     */
    public DatabaseModule()
    {
        databaseModule = this;
        databaseManager = new AcountManager();

        mongoConnect();

        taskChainFactory = BukkitTaskChainFactory.create(plugin);
        System.out.println(ChatColor.DARK_AQUA + "[DatabaseModule] De module is succesvol geladen!");
    }

    /**
     * Opent de MongoDB connection en maakt een instance van de playerDB Collection.
     */
    public void mongoConnect()
    {
        try
        {
            String uri = configModule.getConfig().getConfigConfiguration().getString("mongoURI");
            MongoClientURI clientURI = new MongoClientURI(uri);
            MongoClient mongoClient = new MongoClient(clientURI);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Kitpvp");

            playerDBcollection = mongoDatabase.getCollection("playerDB");

            System.out.println(ChatColor.GREEN + "[Kitpvp] Database has been connected!");
        }
        catch (MongoException expetion)
        {
            System.out.println(ChatColor.RED + "Something went wrong with connecting to the database please try again!");
            expetion.printStackTrace();
            plugin.getServer().shutdown();
        }
    }

    public static <T> TaskChain<T> newChain()
    {
        return taskChainFactory.newChain();
    }
}
