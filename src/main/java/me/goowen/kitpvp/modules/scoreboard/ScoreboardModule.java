package me.goowen.kitpvp.modules.scoreboard;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ScoreboardModule
{

    private @Getter AcountManager acountManager = DatabaseModule.getAcountManager();
    private Kitpvp plugin = Kitpvp.getInstance();

    public ScoreboardModule()
    {
        plugin.getLog().info(ChatColor.DARK_AQUA + "[ScoreboardModule] De module is succesvol geladen!");
    }

    /**
     * Maakt het scoreboard van een speler aan en zet deze bij de speler in de sidebar.
     * @param player de speler wiens scoreboard moet worden aangemaakt
     */
    public void setupScoreboard(Player player)
    {
        PlayerDB playerdb = acountManager.getPlayerDBbyUUID(player);
        int kills = playerdb.getKills();
        int deaths = playerdb.getDeaths();
        PlayerScoreboard board = new PlayerScoreboard(player,ChatColor.RED + "" + ChatColor.BOLD + "Kitpvp");
        board.setline(9, ChatColor.GRAY + "Kills:");
        board.setline(8, ChatColor.DARK_PURPLE + "" + ChatColor.WHITE + kills);
        board.setline(7, ChatColor.AQUA + "    ");
        board.setline(6, ChatColor.GRAY + "Deaths:");
        board.setline(5, ChatColor.BLUE + "" + ChatColor.WHITE + deaths);
        board.setline(4, ChatColor.GREEN + "    ");
        board.setline(3, ChatColor.GRAY + "KD-Ratio:");
        board.setline(2, ChatColor.RED + "" + ChatColor.WHITE + calculateKdRatio(kills, deaths));
        board.setline(1, ChatColor.DARK_GREEN + "   ");
        board.setline(0, ChatColor.RED + "play.Kitpvp.nl");
        playerdb.setPlayerScoreboard(board);
        player.setScoreboard(playerdb.getPlayerScoreboard().getScoreBoard());
    }

    /**
     * Update het scoreboard van de speler met de nieuste waardes!
     * @param player de speler wiens scoreboard moet worden geupdate!
     */
    public void updateScoreboard(Player player)
    {
        PlayerDB playerdb = acountManager.getPlayerDBbyUUID(player);
        int kills = playerdb.getKills();
        int deaths = playerdb.getDeaths();
        PlayerScoreboard board = playerdb.getPlayerScoreboard();
        board.editline(8, ChatColor.DARK_PURPLE + "" + ChatColor.WHITE + kills);
        board.editline(5, ChatColor.BLUE + "" + ChatColor.WHITE + deaths);
        board.editline(2, ChatColor.RED + "" + ChatColor.WHITE + calculateKdRatio(kills, deaths));
        playerdb.setPlayerScoreboard(board);
        player.setScoreboard(playerdb.getPlayerScoreboard().getScoreBoard());
    }

    /**
     * Berekent de speler zijn KD Ratio
     * @param kills de kills van de speler
     * @param deaths de deaths van de speler
     * @return de KD Ratio van de speler
     */
    public double calculateKdRatio(int kills, int deaths)
    {
        double kd = Math.round((((double) kills / (double) deaths /100) * 100) * 10) / 10.0;
        if (Double.isNaN(kd))
        {
            return 0;
        }
        else
        {
            return kd;
        }
    }
}
