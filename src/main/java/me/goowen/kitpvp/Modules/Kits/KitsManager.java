package me.goowen.kitpvp.Modules.Kits;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.Modules.Config.ConfigModule;
import me.goowen.kitpvp.Modules.Utilities.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class KitsManager
{
    private ConfigModule configModule = ConfigModule.getConfigModule();
    private Kitpvp plugin = Kitpvp.getInstance();

    public void saveKit(Player player, String name)
    {
        configModule.getKitsConfig().createConfigPath("kits." + name + ".armor", player.getInventory().getArmorContents());
        configModule.getKitsConfig().createConfigPath("kits." + name + ".items", player.getInventory().getContents());
        configModule.getKitsConfig().getConfigConfiguration().set("kits." + name + ".icon", new ItemBuilder(player.getInventory().getItemInMainHand().clone()).setName(ChatColor.DARK_AQUA + name).addLoreLine(ChatColor.GRAY + "" + ChatColor.UNDERLINE + "Click to select this kit").toItemStack());
        configModule.getKitsConfig().saveAsync(configModule.getKitsConfig());
    }

    @SuppressWarnings("unchecked")
    public void giveKit(Player player, String name)
    {
        FileConfiguration kitsConfig = configModule.getKitsConfig().getConfigConfiguration();
        ItemStack[] content = ((List<ItemStack>) kitsConfig.get("kits."+ name + ".armor")).toArray(new ItemStack[0]);
        player.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) kitsConfig.get("kits."+ name + ".items")).toArray(new ItemStack[0]);
        player.getInventory().setContents(content);
    }

    public void KitsMenu(Player player)
    {
        Inventory kits = plugin.getServer().createInventory(null, 9, ChatColor.DARK_AQUA + "Kies een Kit!");
        for(String s : configModule.getKitsConfig().getConfigConfiguration().getConfigurationSection("kits").getKeys(false))
        {
            kits.addItem(configModule.getKitsConfig().getConfigConfiguration().getItemStack("kits." + s + ".icon"));
        }
        player.openInventory(kits);
    }

}