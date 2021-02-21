package me.goowen.kitpvp.modules.lobby;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class LobbyModule
{

    public static @Getter LobbyModule lobbyModule;
    public static @Getter LobbyManager lobbyManager;
    private Kitpvp plugin = Kitpvp.getInstance();

    /**
     * Maakt instances aan voor de Lobbymodule en Lobbymanager
     * en registreert het lobbyListener event en het setlobby command.
     */
    public void LobbyModule()
    {
        lobbyModule = this;
        lobbyManager = new LobbyManager();
        Bukkit.getPluginManager().registerEvents(new LobbyListener(), plugin);
        plugin.getCommand("setlobby").setExecutor(new LobbyCommand());
        System.out.println(ChatColor.DARK_AQUA + "[lobbyModule] De module is succesvol geladen!");
    }
}
