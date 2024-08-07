package io.github.xitadoo.freecam.commands;

import io.github.xitadoo.freecam.Main;
import io.github.xitadoo.freecam.event.PlayerFreecamEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/
public class Freecam implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) return true;

        int limit = getDistanceLimit(((Player) s).getPlayer());
        if (limit == 0) {
            s.sendMessage("§cVocê não possui permissão para usar o freecam.");
            return true;
        }

        if (Main.getInstance().getEntityManager().containsPlayer(((Player) s).getPlayer())) {
            Main.getInstance().getEntityManager().removeEntity(((Player) s).getPlayer());
            s.sendMessage("§cModo freecam desativado.");
            return true;
        }
        Player player = (Player)s;
        PlayerFreecamEvent event = new PlayerFreecamEvent(player, player.getLocation());
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return true;
        }

        Main.getInstance().getEntityManager().newEntity(((Player) s).getPlayer(), limit);
        s.sendMessage("§aVocê entrou em freecam. Lembre-se que está definido para uma distância máxima de §f§n"
                +Main.getInstance().getEntityManager().getBounds(((Player) s).getPlayer()));
        return false;
    }


    private int getDistanceLimit(Player p) {
        int limit = 0;
        for (PermissionAttachmentInfo perm : p.getEffectivePermissions()) {
            if (perm.getPermission().startsWith("freecam.")) {
                try {
                    int newLimit = Integer.parseInt(perm.getPermission().replace("freecam.", ""));
                    if (newLimit > limit) {
                        limit = newLimit;
                    }
                } catch (Throwable e) {}
            }
        }

        return limit;
    }
}
