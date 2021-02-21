package me.goowen.kitpvp.modules.eventlisteners.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AfterPlayerJoinEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final Player player;

    public AfterPlayerJoinEvent(Player player)
    {
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

    public Player getPlayer()
    {
        return player;
    }
}
