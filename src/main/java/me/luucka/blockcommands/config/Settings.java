package me.luucka.blockcommands.config;

import lombok.Getter;
import me.luucka.blockcommands.BlockCommandsPlugin;
import me.luucka.blockcommands.CommandToBlock;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Settings {

    private final BlockCommandsPlugin plugin;

    private final BaseConfiguration config;

    @Getter
    private final Map<String, CommandToBlock> commandsToBlock = new HashMap<>();

    public Settings(final BlockCommandsPlugin plugin) {
        this.plugin = plugin;
        this.config = new BaseConfiguration(new File(plugin.getDataFolder(), "config.yml"), "/config.yml");
        reloadConfig();
    }

    public void reloadConfig() {
        config.load();
        _loadCommandsToBlock();
    }

    private void _loadCommandsToBlock() {
        commandsToBlock.clear();
        final Set<String> keys = config.getKeys("commands");
        for (final String k : keys) {
            final String cmd = config.getString("commands." + k + ".command", "");
            final String perm = config.getString("commands." + k + ".permission", "");
            final String msg = config.getString("commands." + k + ".message", "");
            commandsToBlock.put(cmd, new CommandToBlock(perm, msg));
        }
    }

}
