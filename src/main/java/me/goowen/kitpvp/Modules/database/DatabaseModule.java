package me.goowen.kitpvp.Modules.database;

import me.goowen.kitpvp.Modules.database.DatabaseManager.DatabaseManager;

public class DatabaseModule
{
    private static DatabaseModule databaseModule;

    public void DatabaseModule()
    {
        databaseModule = this;
        new DatabaseManager().MongoConnect();
    }
}
