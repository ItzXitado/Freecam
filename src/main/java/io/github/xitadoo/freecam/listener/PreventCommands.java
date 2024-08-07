package io.github.xitadoo.freecam.listener;

import io.github.xitadoo.freecam.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/
public class PreventCommands implements Listener {

    @EventHandler
    public void preventCommandUsage(PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        if (Main.getInstance().getEntityManager().containsPlayer(playerCommandPreprocessEvent.getPlayer())) {
            if (!playerCommandPreprocessEvent.getMessage().equalsIgnoreCase("/freecam")) {
                playerCommandPreprocessEvent.setCancelled(true);
                playerCommandPreprocessEvent.getPlayer().sendMessage("§cVocê não pode digitar comandos enquanto está no modo espetador.");
            }
        }
    }
}
