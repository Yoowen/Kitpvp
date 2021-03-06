package me.goowen.kitpvp.modules.kits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitsCommand implements CommandExecutor
{
    private KitsManager kitsManager = KitsModule.getKitsManager();

    /**
     *
     * @param sender Degene die het command uitvoert.
     * @param command ongebruikt
     * @param label ongebruikt
     * @param args De argumenten die meer worden gegeven met het command!
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only a player can use this command");
            return true;
        }

        if (!sender.hasPermission("kitpvp.kits"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;
        if (player.getInventory().getItemInMainHand() == null)
        {
            sender.sendMessage(ChatColor.RED + "You do not have a item in your hand as icon!");
            return true;
        }

        if (!(args.length == 2))
        {
            sender.sendMessage(ChatColor.RED + "Arguments are not correct use /createkit <create/delete> <name>!");
            return true;
        }

        switch(args[0])
        {
            case "create":
                kitsManager.saveKit(player, args[1]);
                sender.sendMessage(ChatColor.DARK_AQUA + "kit succesfully saved under the name " + args[1]);
                break;
            case "delete":
                kitsManager.deleteKit(args[1]);
                sender.sendMessage(ChatColor.DARK_AQUA + args[1] + " has succesfully been deleted!");
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Arguments are not correct use /createkit <create/delete> <name>!");
                break;
        }
        return true;
    }
}
