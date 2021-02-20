package me.goowen.kitpvp.Modules.EventListeners.Events;

import me.goowen.kitpvp.Modules.Lobby.LobbyManager;
import me.goowen.kitpvp.Modules.Lobby.LobbyModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import me.goowen.kitpvp.Modules.scoreboard.ScoreboardModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener
{
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();
    private ScoreboardModule scoreboardModule = ScoreboardModule.getScoreboardModule();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();

    @EventHandler
    public void OnDeath(PlayerDeathEvent event)
    {
        if (event.getEntity().getKiller() instanceof Player)
        {
            Player killer = event.getEntity().getKiller().getPlayer();
            PlayerDB killerDB = databaseManager.getPlayerDBbyUUID(killer);
            killerDB.addKills(1);
        }
        Player victim = event.getEntity().getPlayer();
        PlayerDB victimDB = databaseManager.getPlayerDBbyUUID(victim);
        victimDB.addDeaths(1);
        scoreboardModule.updateScoreboard(victim);
        event.setDeathMessage(null);
    }

    @EventHandler
    public void OnRespawn(PlayerRespawnEvent event)
    {
        event.setRespawnLocation(event.getPlayer().getWorld().getSpawnLocation());
        lobbyManager.lobbycheck(event.getPlayer());
    }
    @EventHandler
    public void OnKill(EntityDeathEvent event)
    {
        if (event.getEntity().getKiller() instanceof Player)
        {
            Player killer = event.getEntity().getKiller().getPlayer();
            PlayerDB killerDB = databaseManager.getPlayerDBbyUUID(killer);
            killerDB.addKills(1);
            scoreboardModule.updateScoreboard(killer);
        }
    }
}
