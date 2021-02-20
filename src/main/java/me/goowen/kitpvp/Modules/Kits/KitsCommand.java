package me.goowen.kitpvp.Modules.Kits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitsCommand implements CommandExecutor
{
    private KitsManager kitsManager = KitsModule.getKitsManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            if (sender.hasPermission("kitpvp.spawneditor"))
            {
                Player player = (Player) sender;
                if (player.getInventory().getItemInMainHand() != null)
                {
                    if (args.length == 1)
                    {
                        kitsManager.saveKit(player, args[0]);
                        sender.sendMessage(ChatColor.DARK_AQUA + "kit succesfully saved under the name !");
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "You do not have a item in your hand as icon!");
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
