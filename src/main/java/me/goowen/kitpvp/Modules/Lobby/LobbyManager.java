package me.goowen.kitpvp.Modules.Lobby;

import me.goowen.kitpvp.Modules.Utilities.ItemBuilder;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.AcountManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyManager
{
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();

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
