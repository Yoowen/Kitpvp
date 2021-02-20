package me.goowen.kitpvp.Modules.EventListeners.Events;

import me.goowen.kitpvp.Modules.database.Callbacks.loadingPlayer;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();

    @EventHandler
    public void OnQuit(PlayerQuitEvent event)
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
                        databaseManager.playerDBhashMap.remove(event.getPlayer().getUniqueId());
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

    @EventHandler
    public void OnKick(PlayerKickEvent event)
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
                        databaseManager.playerDBhashMap.remove(event.getPlayer().getUniqueId());
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
