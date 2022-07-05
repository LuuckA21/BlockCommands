package me.luucka.blockcommands.listeners;

import lombok.RequiredArgsConstructor;
import me.luucka.blockcommands.BlockCommandsPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {

    private final BlockCommandsPlugin plugin;

    @EventHandler
    public void onPreProcessCommand(final PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        final String command = event.getMessage();

        if (plugin.getSettings().getCommandsToBlock().containsKey(command)) {
            final String perm = plugin.getSettings().getCommandsToBlock().get(command).permission();
            if (perm.length() >= 1) {
                if (!player.hasPermission(perm)) {
                    final String msg = plugin.getSettings().getCommandsToBlock().get(command).message();
                    player.sendMessage(MiniMessage.miniMessage().deserialize(msg));
                    event.setCancelled(true);
                }
            }
        }
    }

}
