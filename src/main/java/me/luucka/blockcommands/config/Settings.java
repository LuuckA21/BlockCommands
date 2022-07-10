package me.luucka.blockcommands.config;

import lombok.Getter;
import me.luucka.blockcommands.BlockCommandsPlugin;
import me.luucka.blockcommands.CommandToBlock;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Settings {

    private final BlockCommandsPlugin plugin;

    private final BaseConfiguration config;

    @Getter
    private final Set<CommandToBlock> commandsToBlock = new HashSet<>();

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
        final Set<String> commands = config.getKeys("commands");
        for (final String cmd : commands) {
            final String perm = config.getString("commands." + cmd + ".permission", "");
            final String msg = config.getString("commands." + cmd + ".message", "");
            commandsToBlock.add(new CommandToBlock(cmd, perm, msg));
        }
    }

}
