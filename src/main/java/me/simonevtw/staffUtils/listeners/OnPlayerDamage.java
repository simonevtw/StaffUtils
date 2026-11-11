package me.simonevtw.staffUtils.listeners;

import me.simonevtw.staffUtils.commands.FreezeCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamage implements Listener {
    @EventHandler
    public void OnPlayerDamage(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();

        if(FreezeCommand.frozenPlayers.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }

    }
}