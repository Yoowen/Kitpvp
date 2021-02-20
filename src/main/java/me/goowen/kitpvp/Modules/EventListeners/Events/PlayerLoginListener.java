package me.goowen.kitpvp.Modules.EventListeners.Events;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.EventListeners.CustomEvents.AfterPlayerJoinEvent;
import me.goowen.kitpvp.Modules.Lobby.LobbyManager;
import me.goowen.kitpvp.Modules.Lobby.LobbyModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import me.goowen.kitpvp.Modules.database.Callbacks.loadingPlayer;
import me.goowen.kitpvp.Modules.scoreboard.ScoreboardModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();
    private ScoreboardModule scoreboardModule = ScoreboardModule.getScoreboardModule();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Laat de Speler in vanuit de database wanneer de speler klaar is met inladen roept hij het AfterLogin Event aan.
     * @param event
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnLogin(PlayerLoginEvent event)
    {
        databaseManager.load(event.getPlayer(),  new loadingPlayer()
                {
                    @Override
                    public void waiting() {
                    }

                    @Override
                    public void fetching() {
                    }

                    @Override
                    public void done(PlayerDB playerdb) {
                        AfterPlayerJoinEvent joinevent = new AfterPlayerJoinEvent(event.getPlayer());
                        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(joinevent));
                    }

                    @Override
                    public void error(String err) {
                        System.out.println(err);
                        event.getPlayer().kickPlayer(ChatColor.RED + "Er is iets misgegaan bij het inladen van jou data en daarom hebben we jou gekickt!");
                    }

                    @Override
                    public void welcome() {
                    }
                }
        );
    }

    /**
     * Voert de functies uit die moeten gebeuren direct nadat de speler is ingelogt en ingeladen vanuit de database!
     * Ze het scoreboard op voor de speler
     * Teleporteert de speler naar de ingame lobby
     * Zet de speler in lobby mode indien nodig!
     * @param e
     */
    @EventHandler
    public void afterLogin(AfterPlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        scoreboardModule.setupScoreboard(player);
        player.teleport(player.getWorld().getSpawnLocation());
        lobbyManager.lobbycheck(player);
    }

}
