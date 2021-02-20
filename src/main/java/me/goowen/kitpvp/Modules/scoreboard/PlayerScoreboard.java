package me.goowen.kitpvp.Modules.scoreboard;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public class PlayerScoreboard
{
    private Scoreboard scoreboard;
    private @Getter HashMap<Integer, String> currentlines = new HashMap<Integer, String>();

    /**
     * Creates a Scoreboard specifficly for the player and addes him to it.
     *
     * @param player the owner of this scoreboard.
     * @param displayname The Display name of the scoreboard.
     */
    public PlayerScoreboard(Player player, String displayname)
    {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Scoreboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + displayname);
        this.scoreboard = scoreboard;
        player.setScoreboard(scoreboard);
    }

    /**
     * sets a line in the scoreboard.
     * @param linenumber number of the line that we want to set.
     * @param line the text for the line we want to set.
     */
    public void setline(int linenumber, String line)
    {
        Score score = scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(line);
        score.setScore(linenumber);
        currentlines.put(linenumber, line);
    }

    /**
     * Edits a existing line in the scoreboard.
     * @param linenumber number of the line that we want to edit
     * @param line the new text for the line we want.
     */
    public void editline(int linenumber, String line)
    {
        scoreboard.resetScores(currentlines.get(linenumber));
        Score score = scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(line);
        score.setScore(linenumber);
        currentlines.put(linenumber, line);
    }

    /**
     * Returns the player's current scoreboard saved in this class.
     * @return
     */
    public Scoreboard getScoreBoard() {
        return scoreboard;

    }
}
