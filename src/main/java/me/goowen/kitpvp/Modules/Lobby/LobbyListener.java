package me.goowen.kitpvp.Modules.Lobby;

import me.goowen.kitpvp.Modules.Kits.KitsManager;
import me.goowen.kitpvp.Modules.Kits.KitsModule;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyListener implements Listener
{
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();
    private LobbyManager lobbyManager = LobbyModule.getLobbyManager();
    private KitsManager kitsManager = KitsModule.getKitsManager();

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

    @EventHandler
    public void LobbyBook(PlayerInteractEvent event)
    {
        if (event.getPlayer().getInventory().getItemInMainHand().equals(lobbyManager.kitselector()))
        {
            System.out.println(kitsManager);
            kitsManager.KitsMenu(event.getPlayer());
        }
    }
}
