package me.goowen.kitpvp.Modules.database.DatabaseManager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.ChatColor;

public class DatabaseManager
{
    private static DatabaseManager databaseManager;
    private MongoCollection<Document> playerDB;

    public void MongoConnect()
    {
        databaseManager = this;
        String uri = "mongodb+srv://goowen:admin@cluster1.mpyu5.mongodb.net/test";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("Kitpvp");
        playerDB = mongoDatabase.getCollection("playerDB");

        System.out.println(ChatColor.GREEN + "[Kitpvp] Database has been connected!");
    }

    public MongoCollection<Document> getPlayerDB()
    {
        return playerDB;
    }
}
