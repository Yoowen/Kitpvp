package me.goowen.kitpvp.modules.spawns;

import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.config.ConfigModule;
import me.goowen.kitpvp.modules.kits.KitsModule;
import me.goowen.kitpvp.modules.utilities.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class SpawnsManager
{
    private ConfigModule configModule = Kitpvp.getConfigModule();
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Voegt een spawn locatie toe aan de lijst met mogenlijke spawn locaties.
     * @param location the locations that's needs to be added.
     */
    public void addlocation(Location location)
    {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        World w = location.getWorld();
        String spawnlocation = x + "_" + y + "_" + z + "_" + w.getName();
        ArrayList<String> list = (ArrayList<String>) configModule.getSpawnsConfig().getConfigConfiguration().getStringList("locations");
        list.add(spawnlocation);
        configModule.getSpawnsConfig().getConfigConfiguration().set("locations", list);
        configModule.getSpawnsConfig().saveAsync(configModule.getSpawnsConfig());
        plugin.getLog().info("locations added at" +  spawnlocation);
    }

    /**
     * verwijderdt een spawn locatie uit de lijst met mogenlijke locaties.
     * @param location ,De locatie die moet worden verwijderd.
     */
    public void removelocation(Location location)
    {
        if (isSpawn(location))
        {
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            World w = location.getWorld();
            String spawnlocation = x + "_" + y + "_" + z + "_" + w.getName();
            ArrayList<String> list = (ArrayList<String>) configModule.getSpawnsConfig().getConfigConfiguration().getStringList("locations");
            list.remove(spawnlocation);
            configModule.getSpawnsConfig().getConfigConfiguration().set("locations", list);
            configModule.getSpawnsConfig().saveAsync(configModule.getSpawnsConfig());
            plugin.getLog().info("locations removed at" +  spawnlocation);

        }
    }

    /**
     * Kijkt of de spelers huidige locatie een spawn locatie is.
     * @param location de locatie die word gecontroleerd.
     * @return
     */
    public boolean isSpawn(Location location) {
        if (configModule.getSpawnsConfig().getConfigConfiguration().contains("locations"))
        {

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            String world = location.getWorld().getName();

            String ConfigName = x + "_" + y + "_" + z + "_" + world;
            // s = X_Y_Z_WORLDNAME
            for (String s : configModule.getSpawnsConfig().getConfigConfiguration().getStringList("locations"))
            {
                if (s.equalsIgnoreCase(ConfigName))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Toont alle huidig mogenlijke spawnlocaties voor de speler
     * @param player who will see the locations
     */
    public void showSpawns(Player player) {
        if (configModule.getSpawnsConfig().getConfigConfiguration().contains("locations"))
        {
            for (String s : configModule.getSpawnsConfig().getConfigConfiguration().getStringList("locations"))
            {
                // s = X_Y_Z_WORLDNAME
                String[] location = s.split("_");
                int x = Integer.parseInt(location[0]);
                int y = Integer.parseInt(location[1]);
                int z = Integer.parseInt(location[2]);
                String world = location[3];

                Location blockLoc = new Location(Bukkit.getWorld(world), x, y, z);
                player.sendBlockChange(blockLoc, Material.CYAN_CONCRETE, (byte) 9);

            }
        }
        else
        {
            plugin.getLog().warning(ChatColor.RED + "There currently don't exist any spawn locations");
        }
    }

    /**
     * Verbergt alle bestaande spawnlocaties voor de speler
     * @param player
     */
    public void hideSpawns(Player player) {
        if (configModule.getSpawnsConfig().getConfigConfiguration().contains("locations"))
        {
            for (String s : configModule.getSpawnsConfig().getConfigConfiguration().getStringList("locations"))
            {
                // s = X_Y_Z_WORLDNAME
                String[] location = s.split("_");
                int x = Integer.parseInt(location[0]);
                int y = Integer.parseInt(location[1]);
                int z = Integer.parseInt(location[2]);
                String world = location[3];

                Location blockLoc = new Location(Bukkit.getWorld(world), x, y, z);
                player.sendBlockChange(blockLoc, Material.AIR, (byte) 0);
            }
        }
        else
        {
            plugin.getLog().warning(ChatColor.RED + "There currently don't exist any spawn locations");
        }
    }

    /**
     * Deze Functie returnt een random spawnlocatie van de lijst met aangemaakte spawnlocatie's
     * @return random spawnlocatie
     */
    public Location newSpawnlocation()
    {
        ArrayList<String> list = (ArrayList<String>) configModule.getSpawnsConfig().getConfigConfiguration().getStringList("locations");
        String string = list.get(new Random().nextInt(list.size()));

        String[] splitlocation = string.split("_");
        int x = Integer.parseInt(splitlocation[0]);
        int y = Integer.parseInt(splitlocation[1]);
        int z = Integer.parseInt(splitlocation[2]);
        String world = splitlocation[3];

        Location location = new Location(Bukkit.getWorld(world), x, y, z);
        return location;
    }

    /**
     * Deze functie returnt de spawneditor als item, dit item heb je nodig om spawns te plaatsen.
     * @return spawnEditor
     */
    public ItemStack spawnEditor()
    {
        ItemStack spawneditor = new ItemBuilder(Material.CYAN_CONCRETE).setName(ChatColor.DARK_AQUA + "Spawn Editor").addLoreLine(ChatColor.WHITE + "Place this to add a spawn location").addLoreLine(ChatColor.WHITE + "Break this block to remove a spawn location").toItemStack();
        return spawneditor;
    }
}
