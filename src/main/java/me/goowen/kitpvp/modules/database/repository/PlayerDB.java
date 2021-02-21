package me.goowen.kitpvp.modules.database.repository;

import lombok.Getter;
import lombok.Setter;
import me.goowen.kitpvp.modules.scoreboard.PlayerScoreboard;

public class PlayerDB
{
    @Setter @Getter
    private String name;
    @Setter @Getter
    private String uuid;
    @Setter @Getter
    private int kills;
    @Setter @Getter
    private int deaths;
    @Setter @Getter
    private PlayerScoreboard playerScoreboard;
    @Getter @Setter
    private boolean SpawnEditmode;
    @Getter @Setter
    private boolean lobbymode;

    public PlayerDB(String uuid, String name, int kills, int deaths, boolean SpawnEditmode, boolean lobbymode)
    {
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
        this.SpawnEditmode = SpawnEditmode;
        this.lobbymode = lobbymode;
        this.uuid = uuid;
    }

    public void addKills(int amount)
    {
        this.kills = this.kills + amount;
    }

    public void addDeaths(int amount)
    {
        this.deaths = this.deaths + amount;
    }
}
