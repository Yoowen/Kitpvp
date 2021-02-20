package me.goowen.kitpvp.Modules.Lobby;

import lombok.Getter;
import me.goowen.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class LobbyModule
{

    public static @Getter LobbyModule lobbyModule;
    public static @Getter LobbyManager lobbyManager;
    private Kitpvp plugin = Kitpvp.getInstance();

    public void LobbyModule()
    {
        lobbyModule = this;
        lobbyManager = new LobbyManager();
        Bukkit.getPluginManager().registerEvents(new LobbyListener(), plugin);
        System.out.println(ChatColor.BLUE + "[lobbyModule] De module is succesvol geladen!");
    }
}
