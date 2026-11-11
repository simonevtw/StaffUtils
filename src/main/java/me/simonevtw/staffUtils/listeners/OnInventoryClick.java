package me.simonevtw.staffUtils.listeners;


import me.simonevtw.staffUtils.commands.FreezeCommand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnInventoryClick implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
        HumanEntity humanEntity = e.getWhoClicked();

        if (!(humanEntity instanceof Player p)){
            return;
        }

        if(FreezeCommand.frozenPlayers.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }

    }
}
