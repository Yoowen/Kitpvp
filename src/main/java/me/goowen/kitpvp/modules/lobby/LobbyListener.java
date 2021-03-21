package me.goowen.kitpvp.modules.lobby;

import me.goowen.kitpvp.modules.kits.KitsManager;
import me.goowen.kitpvp.modules.kits.KitsModule;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyListener implements Listener
{
    private AcountManager acountManager = DatabaseModule.getAcountManager();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();
    private KitsManager kitsManager = KitsModule.getKitsManager();

    /**
     * event om te controleren of de speler een item in zijn inventory probeert te verplaatsen terwijl ze in de lobby zitten.
     * @param event ,Het opgeroepen event
     */
    @EventHandler
    public void lobbyInventoryEvent(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        PlayerDB playerDB = acountManager.getPlayerDBbyUUID(player);
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
    public void lobbyBook(PlayerInteractEvent event)
    {
        if (event.getPlayer().getInventory().getItemInMainHand().equals(lobbyManager.kitselector()))
        {
            kitsManager.kitsMenu(event.getPlayer());
        }
    }
}
