package me.goowen.kitpvp.modules.spawns;

import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnsCommand implements CommandExecutor
{
    private SpawnsManager spawnsManager = SpawnsModule.getSpawnsManager();
    private AcountManager databaseManager = DatabaseModule.getDatabaseManager();

    /**
     * Als het commando is afgevuurd, controleren we eerst of het een speler is die het heeft afgevuurd.
     * Vervolgens kijken we of de afzender de juiste toestemming heeft.
     * We halen de speler uit de database en kijken in welke modus hij / zij is.
     * Vanaf dat moment laden of lossen we de spawnlocaties in de spelersradius.
     *
     * @param sender de afzender van het command.
     * @param command ongebruikt
     * @param s ongebruikt
     * @param args de argumenten die met het commando worden verzonden
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
       if (sender instanceof Player)
       {
           if (sender.hasPermission("kitpvp.spawneditor"))
           {
               if (args.length == 0)
               {
                   Player player = (Player) sender;
                   PlayerDB playerDB = databaseManager.getPlayerDBbyUUID(player);
                   if (!playerDB.isSpawnEditmode())
                   {
                       player.getInventory().addItem(spawnsManager.spawnEditor());
                       playerDB.setSpawnEditmode(true);
                       player.sendMessage(ChatColor.DARK_AQUA + "You're now in spawn editmode, use this command again to toggle!");
                       spawnsManager.showSpawns(player);
                   }
                   else
                   {
                       player.getInventory().removeItem(spawnsManager.spawnEditor());
                       playerDB.setSpawnEditmode(false);
                       player.sendMessage(ChatColor.DARK_AQUA + "You're now out of spawn editmode, use this command again to toggle!");
                       spawnsManager.hideSpawns(player);
                   }
               }
               else
               {
                   sender.sendMessage(ChatColor.RED + "Wrong usage, use /spawneditmode!");
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
