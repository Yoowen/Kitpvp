package me.goowen.kitpvp.Modules.Utilities;


import org.bukkit.Bukkit;

public class Log
{

    public static final Log INFO = new Log("[INFO]");
    public static final Log ERROR = new Log("[ERROR]");
    public static final Log DEBUG = new Log("[DEBUG]");
    protected String logtype;

    public Log(String prefix)
    {
        this.logtype = prefix;
    }

    public void send(String message)
    {
        Bukkit.getConsoleSender().sendMessage("[KitPVP] " + logtype + " " + message);
    }
}
