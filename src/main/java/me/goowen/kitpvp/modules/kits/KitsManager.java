package me.goowen.kitpvp.modules.kits;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.config.ConfigModule;
import me.goowen.kitpvp.modules.utilities.ItemBuilder;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class KitsManager
{
    private ConfigModule configModule = ConfigModule.getConfigModule();
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Slaat de kit op in een config vanuit de spelers inventory op basis van naam!
     * @param player de speler wiens inventory moet worden opgeslagen als kit!
     * @param name de naam van de kit!
     */
    public void saveKit(Player player, String name)
    {
        configModule.getKitsConfig().getConfigConfiguration().set("kits." + name + ".armor", player.getInventory().getArmorContents());
        configModule.getKitsConfig().getConfigConfiguration().set("kits." + name + ".items", player.getInventory().getContents());
        configModule.getKitsConfig().getConfigConfiguration().set("kits." + name + ".icon", new ItemBuilder(player.getInventory().getItemInMainHand().clone()).setName(ChatColor.DARK_AQUA + name).addLoreLine(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Click to select this kit").toItemStack());
        configModule.getKitsConfig().saveAsync(configModule.getKitsConfig());
    }

    public void deleteKit(String name)
    {
        configModule.getKitsConfig().getConfigConfiguration().set("kits." + name, null);
        configModule.getKitsConfig().saveAsync(configModule.getKitsConfig());
    }

    /**
     * Haalt de kit op uit de config en zet hem om in een inventory.
     * Geeft de zojuist opgehaalde kit aan de speler.
     * @param player de speler die de kit moet krijgen.
     * @param name de naam van de kit die de speler moet krijgen.
     */
    @SuppressWarnings("unchecked")
    public void giveKit(Player player, String name)
    {
        FileConfiguration kitsConfig = configModule.getKitsConfig().getConfigConfiguration();

        //Haalt de armor op een geeft deze.
        ItemStack[] content = ((List<ItemStack>) Objects.requireNonNull(kitsConfig.get("kits." + name + ".armor"))).toArray(new ItemStack[0]);
        player.getInventory().setArmorContents(content);

        //Haalt de Items in de inventory op een geeft deze.
        content = ((List<ItemStack>) Objects.requireNonNull(kitsConfig.get("kits." + name + ".items"))).toArray(new ItemStack[0]);
        player.getInventory().setContents(content);
    }


    /**
     * Maakt het kits menu aan en toont hem aan de speler.
     * @param player de speler die het menu moet krijgen
     */
    public void kitsMenu(Player player)
    {
        try
        {
            //Maakt de inventory aan.
            Inventory kits = plugin.getServer().createInventory(null, 9, ChatColor.DARK_AQUA + "Kies een Kit!");

            for(String name : configModule.getKitsConfig().getConfigConfiguration().getConfigurationSection("kits").getKeys(false))
            {
                kits.addItem(configModule.getKitsConfig().getConfigConfiguration().getItemStack("kits." + name + ".icon"));
            }

            //Toont(Opent) de inventory aan de speler.
            player.openInventory(kits);
        }
        catch (NullPointerException exeption)
        {
            player.sendMessage(ChatColor.RED + "There are not current kits, please make some!");
        }
    }

}