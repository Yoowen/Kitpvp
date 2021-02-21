package me.goowen.kitpvp.modules.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class KitsCommandTabCompleter implements TabCompleter {

    /**
     *Voegt een tab Completer toe voor het command kits.
     *
     * @param sender de uivoerder van het command!
     * @param command ongebruikt
     * @param label ongebruikt
     * @param args argument mee gegeven met het command!
     * @return
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        List<String> possibleArgs = new ArrayList<>();

        if (args.length == 1) {
            possibleArgs.add("create");
            possibleArgs.add("delete");

            possibleArgs = changeByUseInput(possibleArgs, args[0]);
        }
        return possibleArgs;
    }

    /**
     *
     * @param Possibleargs
     * @param args
     * @return
     */
    private List<String> changeByUseInput(List<String> Possibleargs, String args) {

        for (int i = 0; i < Possibleargs.size(); i++)
        {
            if (!Possibleargs.get(i).toLowerCase().startsWith(args.toLowerCase()))
            {
                Possibleargs.remove(i);
                i--;
            }
        }
        return Possibleargs;
    }
}
