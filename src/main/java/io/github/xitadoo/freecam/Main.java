package io.github.xitadoo.freecam;

import io.github.xitadoo.freecam.commands.Freecam;
import io.github.xitadoo.freecam.entity.manager.EntityManager;
import io.github.xitadoo.freecam.listener.EntityDamageListener;
import io.github.xitadoo.freecam.listener.InteractionListener;
import io.github.xitadoo.freecam.listener.PlayerLeave;
import io.github.xitadoo.freecam.listener.PreventCommands;
import io.github.xitadoo.freecam.tasks.FreecamTask;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author : ItzXitadoo
 * @project : freecam
 * @created : 07/08/2024, Wednesday
 **/

@Getter
public class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    private EntityManager entityManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.entityManager = new EntityManager();

        getCommand("freecam").setExecutor(new Freecam());
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PreventCommands(), this);
        getServer().getPluginManager().registerEvents(new InteractionListener(), this);

        new FreecamTask();
    }

    @Override
    public void onDisable() {

    }

}
