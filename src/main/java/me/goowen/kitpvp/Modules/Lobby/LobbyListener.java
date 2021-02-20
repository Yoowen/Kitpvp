package me.goowen.kitpvp.Modules.Lobby;

import me.goowen.kitpvp.Modules.Kits.KitsManager;
import me.goowen.kitpvp.Modules.Kits.KitsModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyListener implements Listener
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();
    private KitsManager kitsManager = KitsModule.getKitsManager();

    /**
     * event om te controleren of de speler een item in zijn inventory probeert te verplaatsen terwijl ze in de lobby zitten.
     * @param event ,Het opgeroepen event
     */
    @EventHandler
    public void LobbyInventoryEvent(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        PlayerDB playerDB = databaseManager.getPlayerDBbyUUID(player);
        if (playerDB.isLobbymode() && !player.getGameMode().equals(GameMode.CREATIVE))
        {
            event.setCancelled(true);
        }
    }

    /**
     * event om te kijken of een speler clickt met een kit selector.
     * @param event
     */
    @EventHandler
    public void LobbyBook(PlayerInteractEvent event)
    {
        if (event.getPlayer().getInventory().getItemInMainHand().equals(lobbyManager.kitselector()))
        {
            kitsManager.KitsMenu(event.getPlayer());
        }
    }
}
