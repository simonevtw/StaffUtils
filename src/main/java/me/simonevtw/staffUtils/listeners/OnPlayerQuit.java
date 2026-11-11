package me.simonevtw.staffUtils.listeners;

import me.simonevtw.staffUtils.commands.FreezeCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuit implements Listener {
    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(FreezeCommand.frozenPlayers.contains(p.getUniqueId())) {
            p.setGlowing(false);
            FreezeCommand.frozenPlayers.remove(p.getUniqueId());
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!onlinePlayer.hasPermission("freeze.admin")) {
                continue;
            }

            onlinePlayer.sendRichMessage("<dark_red> " + p.getName() + " <red>è uscito dal server mentre era congelato" );
        }

    }
}
