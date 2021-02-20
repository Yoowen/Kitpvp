package me.goowen.kitpvp.Modules.Spawns;

import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnsCommand implements CommandExecutor
{
    private SpawnsManager spawnsManager = SpawnsModule.getSpawnsManager();
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();

    /**
     * When the command is fired we first check if it's a player who fired it.
     * Then we check if the sender has the right permission.
     * We get the player from the database and check in what mode he/she is.
     * From there on do we load or unload the spawnlocations in the players radius.
     *
     * @param sender the sender of the command.
     * @param command this command.
     * @param s the first argument off the command
     * @param arg the arguments send with the command
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] arg)
    {
       if (sender instanceof Player)
       {
           if (sender.hasPermission("kitpvp.spawneditor"))
           {
               Player player = (Player) sender;
               PlayerDB playerDB = databaseManager.getPlayerDBbyUUID(player);
               if (!playerDB.isSpawnEditmode())
               {
                   player.getInventory().addItem(spawnsManager.SpawnEditor());
                   playerDB.setSpawnEditmode(true);
                   player.sendMessage(ChatColor.DARK_AQUA + "You're now in spawn editmode, use this command again to toggle!");
                   spawnsManager.ShowSpawns(player);
               }
               else
               {
                   player.getInventory().removeItem(spawnsManager.SpawnEditor());
                   playerDB.setSpawnEditmode(false);
                   player.sendMessage(ChatColor.DARK_AQUA + "You're now out of spawn editmode, use this command again to toggle!");
                   spawnsManager.HideSpawns(player);
               }
           }
           else
           {
               sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
           }
       }
       else
       {
           sender.sendMessage(ChatColor.RED + "Only a player can use this command");
       }
       return true;
    }
}
