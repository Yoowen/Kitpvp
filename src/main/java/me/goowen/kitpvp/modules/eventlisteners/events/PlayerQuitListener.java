package me.goowen.kitpvp.modules.eventlisteners.events;

import me.goowen.kitpvp.modules.database.callbacks.loadingPlayer;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();

    /**
     * Slaat de speler op in de Database en verwijderd hem uit de hashmap wanneer hij of zij uitlogt.
     * @param event
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        databaseManager.save(event.getPlayer(),  new loadingPlayer()
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
                        databaseManager.getPlayersList().remove(playerdb);
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
        databaseManager.save(event.getPlayer(),  new loadingPlayer()
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
                        databaseManager.getPlayersList().remove(playerdb);
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
