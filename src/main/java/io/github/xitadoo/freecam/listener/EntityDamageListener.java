package io.github.xitadoo.freecam.listener;

import io.github.xitadoo.freecam.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/
public class EntityDamageListener implements Listener {

    public String getMetadata(Entity entity, String key) {
        List<MetadataValue> metadataValues = entity.getMetadata(key);
        return metadataValues.stream()
                .filter(metadataValue -> metadataValue.getOwningPlugin() == Main.getInstance())
                .findFirst()
                .map(MetadataValue::asString)
                .orElse(null);
    }

    @EventHandler
    public void onZombieTakeDamage(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Zombie) {
            Entity entity = entityDamageByEntityEvent.getEntity();
            if (entity.hasMetadata("FreecamZombie")) {
                String name = getMetadata(entity, "FreecamZombie");
                Main.getInstance().getEntityManager().removeEntity(Bukkit.getPlayer(name));
            }
        }
    }
}
