![App Screenshot](https://i.imgur.com/PKcjBsL.png)

![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/ItzXitado/Freecam/total)


# Introduction and Instructions

The Freecam its a plugin for Minecraft Spigot 1.8 that allows user to enter a "freecam" state. When they enter freecam a zombie is placed at the location they entered, and if that zombie gets damaged the player is teleported back, and removed from the freecam mode.

Also the players have a limit distance that they can be from the zombie, if they exceed it, they will be teleported back.
This distance is defined by a permission, lets say the player has the permission freecam.100, then the max distance is 100 blocks, if its freecam.200 its 200 and so on.

# Language

Currently the plugin messages are in Portuguese, maybe in the future i'll add configurable messages.

# Installation

    1. Download the latest version of the Freecam plugin.
    2. Place the plugin JAR file into your server's `plugins` directory.
    3. Restart your server to load the plugin.


# API for Developers

The `PlayerFreecamEvent` is a custom event that is fired when a player enters freecam mode. You can listen to this event in your plugin and take appropriate actions.

#### Event Properties

- `Player player`: The player who entered freecam mode.
- `Location location`: The location where the player entered freecam mode.
- `boolean cancelled`: Indicates whether the event is cancelled.

#### Event Methods

- `boolean isCancelled()`: Returns whether the event is cancelled.
- `void setCancelled(boolean cancelled)`: Sets the cancellation state of the event..

### Example Usage

```java
import io.github.xitadoo.freecam.event.PlayerFreecamEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerFreecam(PlayerFreecamEvent event) {
        // Handle the event
        Player player = event.getPlayer();
        Location location = event.getLocation();

        // Example: Cancel the event
        event.setCancelled(true);
        
        player.sendMessage("Freecam mode is not allowed!");
    }
}

