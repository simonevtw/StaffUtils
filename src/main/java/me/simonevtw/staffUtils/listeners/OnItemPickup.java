package me.simonevtw.staffUtils.listeners;

import me.simonevtw.staffUtils.commands.FreezeCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;

public class OnItemPickup implements Listener {
    @EventHandler
    public void OnItemPickup(PlayerAttemptPickupItemEvent e) {
        Player p = e.getPlayer();

        if(FreezeCommand.frozenPlayers.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }

    }
}