package me.goowen.kitpvp.modules.database.manager;

import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import me.goowen.kitpvp.modules.database.callbacks.loadingPlayer;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AcountManager
{
    private final ArrayList<PlayerDB> playerslist = new ArrayList<>();
    private DatabaseModule databaseModule = DatabaseModule.getDatabaseModule();

    /**
     * Laat de speler in van de database of wanneer de speler niet bestaat word de speler aangemaakt.
     * De data van de speler word vervolgends opgeslagen in een apparte PlayerDB Class
     * @param player
     * @param loadingPlayer
     */
    public void load(Player player, loadingPlayer loadingPlayer)
    {
        //Laat alles in op nieuwe chain deze draait Async
        databaseModule.newChain().async(() ->
        {
            Document playerdocument = new Document("UUID", player.getUniqueId().toString());
            Document found = DatabaseModule.getPlayerDBcollection().find(playerdocument).first();

            //Kijkt het playerdocument al bestaat
            if (found == null)
            {
                //Maakt een nieuwe playerdocument aan omdat de nog niet bestaat
                playerdocument.append("kills", 0);
                playerdocument.append("deaths", 0);
                playerdocument.append("name", player.getName());
                DatabaseModule.getPlayerDBcollection().insertOne(playerdocument);
                playerslist.add(new PlayerDB(player.getUniqueId().toString(), player.getName(),0,0, false, false));
                System.out.println(ChatColor.AQUA + " is new here added them in Database and loaded...Welkom!");
            }
            else
            {
                int kills = found.getInteger("kills");
                int deaths = found.getInteger("deaths");
                playerslist.add(new PlayerDB(player.getUniqueId().toString(), player.getName(), kills, deaths, false, false));
                System.out.println(ChatColor.LIGHT_PURPLE + player.getName() + " found in Database and loaded...Welkom!");
            }
            loadingPlayer.done(getPlayerDBbyUUID(player));
        }).execute();
    }

    /**
     * Slaat de Speler Async op in de database!
     * @param player
     * @param loadingPlayer
     */
    public void save(Player player, loadingPlayer loadingPlayer)
    {
        databaseModule.newChain().async(() ->
        {
            Document playerdocument = new Document("UUID", player.getUniqueId().toString());
            Document found = DatabaseModule.getPlayerDBcollection().find(playerdocument).first();
            if (found != null)
            {
                int kills = this.getPlayerDBbyUUID(player).getKills();
                int deaths = this.getPlayerDBbyUUID(player).getDeaths();
                Bson updateoperation = new Document("$set", new Document("name", player.getName()).append("kills", kills).append("deaths", deaths));
                DatabaseModule.getPlayerDBcollection().updateOne(found, updateoperation);
                System.out.println(ChatColor.LIGHT_PURPLE + player.getName() + " has been saved in Database");
            }
            loadingPlayer.done(getPlayerDBbyUUID(player));
        }).execute();
    }

    /**
     * Returnt het playerDB Object op basis van de speler die word mee gegeven.
     * @param player de speler waarvan we het playerDB object willen.
     * @return
     */
    public PlayerDB getPlayerDBbyUUID(Player player)
    {
        for (PlayerDB playerDB : playerslist)
        {
            if (playerDB.getUuid().equals(player.getUniqueId().toString()))
            {
                return playerDB;
            }
        }
        System.out.println("Error player could not be loaded from the database by UUID");
        return null;
    }

    public List<PlayerDB> getPlayersList()
    {
        return playerslist;
    }

}
