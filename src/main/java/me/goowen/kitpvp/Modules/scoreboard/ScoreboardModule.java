package me.goowen.kitpvp.Modules.scoreboard;

import lombok.Getter;
import me.goowen.kitpvp.Modules.database.DatabaseModule;
import me.goowen.kitpvp.Modules.database.Manager.DatabaseManager;
import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ScoreboardModule
{
    @Getter
    public static ScoreboardModule scoreboardModule;
    private DatabaseManager databaseManager = DatabaseModule.getDatabaseManager();

    public void ScoreboardModule()
    {
        scoreboardModule = this;
        System.out.println(ChatColor.BLUE + "[ScoreboardModule] De module is succesvol geladen!");
    }

    public void setupScoreboard(Player player)
    {
        PlayerDB playerdb = databaseManager.getPlayerDBbyUUID(player);
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

    public void updateScoreboard(Player player)
    {
        PlayerDB playerdb = databaseManager.getPlayerDBbyUUID(player);
        int kills = playerdb.getKills();
        int deaths = playerdb.getDeaths();
        PlayerScoreboard board = playerdb.getPlayerScoreboard();
        board.editline(8, ChatColor.DARK_PURPLE + "" + ChatColor.WHITE + kills);
        board.editline(5, ChatColor.BLUE + "" + ChatColor.WHITE + deaths);
        board.editline(2, ChatColor.RED + "" + ChatColor.WHITE + calculateKdRatio(kills, deaths));
        playerdb.setPlayerScoreboard(board);
        player.setScoreboard(playerdb.getPlayerScoreboard().getScoreBoard());
    }

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
