package me.luucka.blockcommands.listeners;

import lombok.RequiredArgsConstructor;
import me.luucka.blockcommands.CommandToBlock;
import me.luucka.blockcommands.config.Settings;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Set;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {

    private final Settings settings;

    @EventHandler
    public void onPreProcessCommand(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String command = event.getMessage();

        final Set<CommandToBlock> commandToBlocks = settings.getCommandsToBlock();

        commandToBlocks.stream().filter(commandToBlock -> command.startsWith(commandToBlock.command())).findFirst()
                .ifPresent(commandToBlock -> {
                    if (commandToBlock.permission().length() >= 1) {
                        if (!player.hasPermission(commandToBlock.permission())) {
                            player.sendMessage(MiniMessage.miniMessage().deserialize(commandToBlock.message()));
                            event.setCancelled(true);
                        }
                    }
                });
    }

}
