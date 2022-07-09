package me.luucka.blockcommands.listeners;

import lombok.RequiredArgsConstructor;
import me.luucka.blockcommands.BlockCommandsPlugin;
import me.luucka.blockcommands.CommandToBlock;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {

    private final BlockCommandsPlugin plugin;

    @EventHandler
    public void onPreProcessCommand(final PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        final String command = event.getMessage();

        final Set<CommandToBlock> commandToBlocks = plugin.getSettings().getCommandsToBlock();
        Optional<CommandToBlock> optional = commandToBlocks.stream().filter(cmd -> command.startsWith(cmd.command())).findAny();

        if (optional.isPresent()) {
            final CommandToBlock commandToBlock = optional.get();
            if (commandToBlock.permission().length() >= 1) {
                if (!player.hasPermission(commandToBlock.permission())) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(commandToBlock.message()));
                    event.setCancelled(true);
                }
            }
        }
    }

}
