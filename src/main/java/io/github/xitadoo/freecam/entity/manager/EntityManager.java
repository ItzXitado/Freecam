package io.github.xitadoo.freecam.entity.manager;

import io.github.xitadoo.freecam.entity.FreecamZombie;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/

@Getter
public class EntityManager {

    private final HashMap<Player, FreecamZombie> freecamEntityHashMap;

    public EntityManager() {
        freecamEntityHashMap = new HashMap<>();
    }

    public void newEntity(Player player, int maxDist) {
        Location location = player.getLocation();
        FreecamZombie freecamEntityMob = new FreecamZombie(player.getName(),location, maxDist);
        freecamEntityHashMap.put(player, freecamEntityMob);
        freecamEntityMob.spawn();

        player.setGameMode(GameMode.SPECTATOR);
    }

    public void removeEntity(Player player) {
        if (!freecamEntityHashMap.containsKey(player)) {
            return;
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(freecamEntityHashMap.get(player).getLocation());
        freecamEntityHashMap.get(player).kill();
        freecamEntityHashMap.remove(player);
    }

    public boolean isOutOfBounds(Player player) {
        int distance = Integer.parseInt(format(player.getEyeLocation().distance(freecamEntityHashMap.get(player).getZombie().getEyeLocation())));
        return distance > getBounds(player);
    }

    public int getBounds(Player player) {
        return freecamEntityHashMap.get(player).getMaxDist();
    }

    public int getDistance(Player player) {
        return Integer.parseInt(format(player.getEyeLocation().distance(freecamEntityHashMap.get(player).getZombie().getEyeLocation())));
    }

    public void teleportToBounds(Player player) {
        player.teleport(freecamEntityHashMap.get(player).getLocation());
    }

    public boolean containsPlayer(Player player) {
        return freecamEntityHashMap.containsKey(player);
    }

    private String format(Double value) {
        DecimalFormat format = new DecimalFormat("0");
        return (format.format(value));
    }
}
