package me.goowen.kitpvp.Modules.EventListeners.Events;

import me.goowen.kitpvp.Modules.Lobby.LobbyManager;
import me.goowen.kitpvp.Modules.Lobby.LobbyModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import me.goowen.kitpvp.Modules.scoreboard.ScoreboardModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();
    private ScoreboardModule scoreboardModule = ScoreboardModule.getScoreboardModule();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();

    /**
     * Geeft de killer zijn kill en update zijn scoreboard.
     * Geeft het slachtoffer zijn death en update zijn scoreboard.
     * Zet de Death en Drop message naar null.
     * @param event
     */
    @EventHandler
    public void OnDeath(PlayerDeathEvent event)
    {
        if (event.getEntity().getKiller() instanceof Player)
        {
            Player killer = event.getEntity().getKiller().getPlayer();
            PlayerDB killerDB = databaseManager.getPlayerDBbyUUID(killer);
            killerDB.addKills(1);
            scoreboardModule.updateScoreboard(killer);
        }
        Player victim = event.getEntity().getPlayer();
        PlayerDB victimDB = databaseManager.getPlayerDBbyUUID(victim);
        victimDB.addDeaths(1);
        scoreboardModule.updateScoreboard(victim);
        event.setDeathMessage(null);
        event.getDrops().clear();
    }

    /**
     * Stuurt de speler naar de worldspawn waar de lobby zit
     * en zet hem in lobbymode via de lobbycheck
     * @param event
     */
    @EventHandler
    public void OnRespawn(PlayerRespawnEvent event)
    {
        event.setRespawnLocation(event.getPlayer().getWorld().getSpawnLocation());
        lobbyManager.lobbycheck(event.getPlayer());
    }
}
