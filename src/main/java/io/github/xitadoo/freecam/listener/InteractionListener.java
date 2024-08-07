package io.github.xitadoo.freecam.listener;

import io.github.xitadoo.freecam.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/
public class InteractionListener implements Listener {

    @EventHandler
    public void userInteract(PlayerInteractEvent playerInteractEvent) {
        if (Main.getInstance().getEntityManager().containsPlayer(playerInteractEvent.getPlayer())) {
            playerInteractEvent.setCancelled(true);
        }
    }
}
