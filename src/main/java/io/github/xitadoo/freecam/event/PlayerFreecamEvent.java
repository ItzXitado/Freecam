package io.github.xitadoo.freecam.event;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/

@Getter
public class PlayerFreecamEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Location location;
    private boolean cancelled;

    public PlayerFreecamEvent(Player player, Location location) {
        this.player = player;
        this.location = location;
        this.cancelled = false;
    }


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
