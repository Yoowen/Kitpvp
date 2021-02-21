package me.goowen.kitpvp.modules.database.callbacks;

import me.goowen.kitpvp.modules.database.repository.PlayerDB;

public interface loadingPlayer {

    void waiting();

    void fetching();

    void done(PlayerDB playerDB);

    void error(String err);

    void welcome();
}