package me.luucka.blockcommands.commands;

import lombok.RequiredArgsConstructor;
import me.luucka.blockcommands.BlockCommandsPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ReloadCommand implements CommandExecutor {

    private final BlockCommandsPlugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("blockcommands.admin")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<aqua>BlockCommands <red>You do not have permission!"));
            return true;
        }
        plugin.getSettings().reloadConfig();
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<aqua>BlockCommands <green>reloaded!"));
        return true;
    }
}
