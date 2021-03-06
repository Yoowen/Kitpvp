package me.goowen.kitpvp.modules.lobby;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor
{
    /**
     * Ze De Worldspawn naar de locatie van de speler dit functioneert als het spawnpunt van de lobby
     *
     * @param sender de afzender van het command.
     * @param command ongebruikt
     * @param s ongebruikt
     * @param args ongebruikt
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
       if (!(sender instanceof Player))
       {
           sender.sendMessage(ChatColor.RED + "Only a player can use this command");
           return true;
       }

        if (!(sender.hasPermission("kitpvp.setlobby")))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        if (!(args.length == 0))
        {
            sender.sendMessage(ChatColor.RED + "Wrong usage, use /setlobby!");
            return true;
        }

        Player player = (Player) sender;
        player.getWorld().setSpawnLocation(player.getLocation());
        sender.sendMessage(ChatColor.RED + "De SpawnLocatie van de lobby is succesvol gezet!");
        return true;
    }
}
