package me.goowen.kitpvp.Modules.database.Manager;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Setter;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import me.goowen.kitpvp.Modules.database.Callbacks.loadingPlayer;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AcountManager
{
    private @Setter  MongoCollection<Document> playerDBcollection;
    public HashMap<UUID, PlayerDB> playerDBhashMap;

    private DatabaseModule databaseModule = DatabaseModule.getDatabaseModule();

    public void load(Player player, loadingPlayer loadingPlayer)
    {
        databaseModule.newChain().async(() ->
        {
            Document playerdoc = new Document("UUID", player.getUniqueId().toString());
            Document found = playerDBcollection.find(playerdoc).first();
            if (found == null){
                playerdoc.append("kills", 0);
                playerdoc.append("deaths", 0);
                playerdoc.append("name", player.getName());
                playerDBcollection.insertOne(playerdoc);
                playerDBhashMap.put(player.getUniqueId(), new PlayerDB(player.getName(),0,0, false, false));
                System.out.println(ChatColor.AQUA + "Player created in Database and loaded!");
            }
            else
            {
                int kills = found.getInteger("kills");
                int deaths = found.getInteger("deaths");
                playerDBhashMap.put(player.getUniqueId(), new PlayerDB(player.getName(), kills, deaths, false, false));
                System.out.println(ChatColor.LIGHT_PURPLE + player.getName() + "found in Database and loaded!");
            }
            loadingPlayer.done(getPlayerDBbyUUID(player));
        }).execute();
    }

    public void save(Player player, loadingPlayer loadingPlayer)
    {
        databaseModule.newChain().async(() ->
        {
            Document playerdoc = new Document("UUID", player.getUniqueId().toString());
            Document found = playerDBcollection.find(playerdoc).first();
            if (found != null)
            {
                int kills = this.getPlayerDBbyUUID(player).getKills();
                int deaths = this.getPlayerDBbyUUID(player).getDeaths();
                Bson updateoperation = new Document("$set", new Document("name", player.getName()).append("kills", kills).append("deaths", deaths));
                playerDBcollection.updateOne(found, updateoperation);
                System.out.println(ChatColor.LIGHT_PURPLE + player.getName() + " has been saved in Database");
            }
            loadingPlayer.done(getPlayerDBbyUUID(player));
        }).execute();
    }

    public PlayerDB getPlayerDBbyUUID(Player player)
    {
        return playerDBhashMap.get(player.getUniqueId());
    }

}
