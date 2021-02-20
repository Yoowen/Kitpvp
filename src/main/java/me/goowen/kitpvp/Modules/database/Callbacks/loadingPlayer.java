package me.goowen.kitpvp.Modules.database.Callbacks;

import me.goowen.kitpvp.Modules.database.Repository.PlayerDB;

public interface loadingPlayer {

    void waiting();

    void fetching();

    void done(PlayerDB playerDB);

    void error(String err);

    void welcome();
}