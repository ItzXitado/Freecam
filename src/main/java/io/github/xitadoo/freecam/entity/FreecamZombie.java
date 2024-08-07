package io.github.xitadoo.freecam.entity;

import io.github.xitadoo.freecam.Main;
import io.github.xitadoo.freecam.utils.EntityAI;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/

@Getter
@Setter
public class FreecamZombie {

    private String name;
    private Location location;
    private int maxDist;
    private Zombie zombie;

    public FreecamZombie(String name, Location location, int maxDist) {
        this.name = name;
        this.location = location;
        this.maxDist = maxDist;
    }

    public void spawn() {
        zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        zombie.setMetadata("FreecamZombie", new FixedMetadataValue(Main.getInstance(), name));

        zombie.setCustomName(name);
        zombie.setCustomNameVisible(true);
        zombie.setCanPickupItems(false);
        EntityAI.setAiEnabled((CraftZombie) zombie, false);
        zombie.setBaby(false);

        new BukkitRunnable() {
            @Override
            public void run() {
                zombie.setFireTicks(0);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0L, 1L);
    }

    public void kill() {
        zombie.teleport(new Location(location.getWorld(), 0, -10, 0));
        zombie.setHealth(0);
    }
}
