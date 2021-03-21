package me.goowen.kitpvp.modules.eventlisteners.listeners;

import me.goowen.kitpvp.modules.database.callbacks.LoadingPlayer;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    private AcountManager acountManager = DatabaseModule.getAcountManager();

    /**
     * Slaat de speler op in de Database en verwijderd hem uit de hashmap wanneer hij of zij uitlogt.
     * @param event
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        acountManager.save(event.getPlayer(),  new LoadingPlayer()
                {
                    @Override
                    public void waiting() {
                    }

                    @Override
                    public void fetching() {
                    }

                    @Override
                    public void done(PlayerDB playerdb)
                    {
                        acountManager.getPlayersMap().remove(playerdb);
                    }

                    @Override
                    public void error(String err) {
                        System.out.println(err);
                    }

                    @Override
                    public void welcome() {
                    }
                }
        );
    }

    /**
     * Slaat de speler op in de Database en verwijderd hem uit de hashmap, wanneer hij of zij gekickt wordt.
     * @param event
     */
    @EventHandler
    public void onKick(PlayerKickEvent event)
    {
        acountManager.save(event.getPlayer(),  new LoadingPlayer()
                {
                    @Override
                    public void waiting() {
                    }

                    @Override
                    public void fetching() {
                    }

                    @Override
                    public void done(PlayerDB playerdb)
                    {
                        acountManager.getPlayersMap().remove(playerdb);
                    }

                    @Override
                    public void error(String err) {
                        System.out.println(err);
                    }

                    @Override
                    public void welcome() {
                    }
                }
        );
    }
}
