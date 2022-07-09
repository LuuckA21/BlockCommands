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
        this.settings = new Settings(this);
        getCommand("blockcommands").setExecutor(new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
    }
}
