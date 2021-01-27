package me.goowen.kitpvp.Modules.database.Repository;

import com.google.common.collect.Maps;
import com.mongodb.client.model.Filters;
import me.goowen.kitpvp.Modules.database.DatabaseManager.DatabaseManager;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;

import java.util.Map;

public class PlayerDB
{
    private static final Map<Player, PlayerDB> playerdb = Maps.newHashMap();

    public static PlayerDB get(Player player)
    {
        if (playerdb.containsKey(player))
        {
            return playerdb.get(player);
        }

        PlayerDB playerDB = new PlayerDB(player);
        playerDB.load();

        playerdb.put(player, playerDB);
        return  playerDB;
    }

    private final Player player;
    private final Map<String, Object> values = Maps.newHashMap();
    private DatabaseManager databaseManager;

    public PlayerDB(Player player)
    {
        this.player = player;
    }

    public void load()
    {
        Document playerdb = databaseManager.getPlayerDB().find(Filters.eq("uuid", player.getUniqueId().toString())).first();
        if (playerdb == null)
        {
            this.values.put("name", player.getName());
            this.values.put("kills", 0);
            this.values.put("deaths", 0);
            return;
        }

        this.values.putAll(playerdb);
    }

    public int getKills()
    {
        return NumberConversions.toInt(this.values.get("kills"));
    }

    public int getDeaths()
    {
        return NumberConversions.toInt(this.values.get("deaths"));
    }

    public String getName()
    {
        return this.values.get("name").toString();
    }

    public void addKills(int amount)
    {
        this.values.put("kills", this.getKills() + amount);
    }

    public void addDeaths(int amount)
    {
        this.values.put("deaths", this.getDeaths() + amount);
    }

    public void setName(String name)
    {
        this.values.put("name", name);
    }

    private Document todocument()
    {
        Document document = new Document("uuid", player.getUniqueId().toString());
        this.values.forEach(document::append);
        return document;
    }

    public void update()
    {
        databaseManager.getPlayerDB().updateOne(Filters.eq("uuid", player.getUniqueId().toString()), new Document("$set", this.todocument()));
    }
}
