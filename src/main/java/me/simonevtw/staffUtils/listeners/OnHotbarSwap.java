package me.simonevtw.staffUtils.listeners;

import me.simonevtw.staffUtils.commands.FreezeCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class OnHotbarSwap implements Listener {
    @EventHandler
    public void OnHotbarSwap(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();

        if(FreezeCommand.frozenPlayers.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }

    }
}
