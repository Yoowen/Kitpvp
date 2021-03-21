package me.goowen.kitpvp.modules.eventlisteners.listeners;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.lobby.LobbyManager;
import me.goowen.kitpvp.modules.lobby.LobbyModule;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import me.goowen.kitpvp.modules.scoreboard.ScoreboardModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener
{
    private AcountManager acountManager = DatabaseModule.getAcountManager();
    private ScoreboardModule scoreboardModule = Kitpvp.getScoreboardModule();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();

    /**
     * Geeft de killer zijn kill en update zijn scoreboard.
     * Geeft het slachtoffer zijn death en update zijn scoreboard.
     * Zet de Death en Drop message naar null.
     * @param event
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        if (event.getEntity().getKiller() instanceof Player)
        {
            Player killer = event.getEntity().getKiller().getPlayer();
            PlayerDB killerDB = acountManager.getPlayerDBbyUUID(killer);
            killerDB.addKills(1);
            scoreboardModule.updateScoreboard(killer);
        }
        Player victim = event.getEntity().getPlayer();
        PlayerDB victimDB = acountManager.getPlayerDBbyUUID(victim);
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
    public void onRespawn(PlayerRespawnEvent event)
    {
        event.setRespawnLocation(event.getPlayer().getWorld().getSpawnLocation());
        lobbyManager.lobbycheck(event.getPlayer());
    }
}
