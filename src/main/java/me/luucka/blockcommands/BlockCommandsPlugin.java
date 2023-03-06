package me.luucka.blockcommands;

import lombok.Getter;
import me.luucka.blockcommands.commands.ReloadCommand;
import me.luucka.blockcommands.config.Settings;
import me.luucka.blockcommands.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockCommandsPlugin extends JavaPlugin {

    @Getter
    private Settings settings;

    @Override
    public void onEnable() {
        settings = new Settings(this);
        getCommand("blockcommands").setExecutor(new ReloadCommand(settings));
        getServer().getPluginManager().registerEvents(new PlayerListeners(settings), this);
    }
}
