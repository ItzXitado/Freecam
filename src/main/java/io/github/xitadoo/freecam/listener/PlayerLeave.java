package io.github.xitadoo.freecam.listener;

import io.github.xitadoo.freecam.Main;
import io.github.xitadoo.freecam.entity.manager.EntityManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/
public class PlayerLeave implements Listener {
    
    private final EntityManager entityManager;
    
    public PlayerLeave() {
        this.entityManager = Main.getInstance().getEntityManager();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent playerQuitEvent) {
        if (entityManager.getFreecamEntityHashMap().containsKey(playerQuitEvent.getPlayer())) {
            entityManager.getFreecamEntityHashMap().get(playerQuitEvent.getPlayer()).kill();
            entityManager.getFreecamEntityHashMap().remove(playerQuitEvent.getPlayer());
            playerQuitEvent.getPlayer().setGameMode(GameMode.SURVIVAL);
            playerQuitEvent.getPlayer().teleport(playerQuitEvent.getPlayer().getLocation().getWorld().getSpawnLocation());
        }
    }
}
