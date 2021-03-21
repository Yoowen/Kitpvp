package me.goowen.kitpvp.modules.eventlisteners.events;

import me.goowen.kitpvp.modules.database.DatabaseModule;
import me.goowen.kitpvp.modules.database.manager.AcountManager;
import me.goowen.kitpvp.modules.database.repository.PlayerDB;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerLoadedEvent extends PlayerEvent {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private AcountManager acountManager = DatabaseModule.getAcountManager();

    public PlayerLoadedEvent(Player player)
    {
        super(player);
        this.player = player;
    }

    public static HandlerList getHandlerList()
    {
        return HANDLERS_LIST;
    }

    @Override
    public HandlerList getHandlers()
    {
        return HANDLERS_LIST;
    }

    public PlayerDB getPlayerDB()
    {
        return acountManager.getPlayerDBbyUUID(player);
    }
}
