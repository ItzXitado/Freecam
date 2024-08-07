package io.github.xitadoo.freecam.tasks;

import io.github.xitadoo.freecam.Main;
import io.github.xitadoo.freecam.entity.manager.EntityManager;
import io.github.xitadoo.freecam.utils.ActionBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/
public class FreecamTask extends BukkitRunnable {

    private final EntityManager entityManager;

    public FreecamTask() {
        entityManager = Main.getInstance().getEntityManager();
        runTaskTimerAsynchronously(Main.getInstance(), 0L, 1L);
    }

    @Override
    public void run() {
        for (Player player : entityManager.getFreecamEntityHashMap().keySet()) {
            ActionBar.sendActionBarMessage(player, "§aDistância: §f" + entityManager.getDistance(player));
            if (entityManager.isOutOfBounds(player)) {
                entityManager.teleportToBounds(player);
            }
        }
    }
}
