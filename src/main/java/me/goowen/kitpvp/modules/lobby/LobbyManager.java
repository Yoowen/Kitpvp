package me.goowen.kitpvp.modules.lobby;

import me.goowen.kitpvp.modules.utilities.ItemBuilder;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyManager
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();

    /**
     * Controleert of de speler in lobbymode zit, Zo ja word hij uit lobbymode gehaalt, Zo nee word hij in lobbymode gezet.
     * In alle gevalen word de speler zijn inventory gecleard en word hij gehealt en gefeed!
     * @param player
     */
    public void lobbycheck(Player player)
    {
        PlayerDB playerDB = databaseManager.getPlayerDBbyUUID(player);
        player.getInventory().clear();
        if(playerDB.isLobbymode())
        {
            playerDB.setLobbymode(false);
        }
        else
        {
            playerDB.setLobbymode(true);
            player.getInventory().setItem(4, kitselector());
        }
        player.setHealth(20.0);
        player.setFoodLevel(20);
    }

    public ItemStack kitselector()
    {
        ItemStack kitselector = new ItemBuilder(Material.BOOK).setName(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Kit Selector").toItemStack();
        return kitselector;
    }
}
