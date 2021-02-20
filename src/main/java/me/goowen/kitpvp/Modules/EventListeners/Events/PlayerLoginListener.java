package me.goowen.kitpvp.Modules.EventListeners.Events;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.EventListeners.CustomEvents.AfterPlayerJoinEvent;
import me.goowen.kitpvp.Modules.Lobby.LobbyManager;
import me.goowen.kitpvp.Modules.Lobby.LobbyModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
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
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();
    private ScoreboardModule scoreboardModule = ScoreboardModule.getScoreboardModule();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();
    private Kitpvp plugin = Kitpvp.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnLogin(PlayerLoginEvent event)
    {
        databaseManager.load(event.getPlayer(),  new loadingPlayer()
                {
                    @Override
                    public void waiting() {
                        System.out.println("Waiting");
                        event.getPlayer().sendTitle(ChatColor.DARK_AQUA + "Momentje Geduldt", "We proberen jou spelerdata in te laden!");
                    }

                    @Override
                    public void fetching() {
                        System.out.println("fetching");
                    }

                    @Override
                    public void done(PlayerDB playerdb) {
                        AfterPlayerJoinEvent joinevent = new AfterPlayerJoinEvent(event.getPlayer());
                        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(joinevent));
                    }

                    @Override
                    public void error(String err) {
                        System.out.println(err);
                    }

                    @Override
                    public void welcome() {
                        System.out.println("welcome");
                        event.getPlayer().hideTitle();
                    }
                }
        );
    }

    @EventHandler
    public void afterJoin(AfterPlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        scoreboardModule.setupScoreboard(player);
        player.teleport(player.getWorld().getSpawnLocation());
        lobbyManager.lobbycheck(player);
    }

}
