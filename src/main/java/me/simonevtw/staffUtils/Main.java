package me.simonevtw.staffUtils;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.simonevtw.staffUtils.commands.AiutoCommand;
import me.simonevtw.staffUtils.commands.AssistenzaCommand;
import me.simonevtw.staffUtils.commands.FreezeCommand;
import me.simonevtw.staffUtils.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(FreezeCommand.create());
            event.registrar().register(AssistenzaCommand.create());
            event.registrar().register(AiutoCommand.create());
        });
        this.getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
        this.getServer().getPluginManager().registerEvents(new OnItemDrop(), this);
        this.getServer().getPluginManager().registerEvents(new OnItemPickup(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerDamage(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        this.getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        this.getServer().getPluginManager().registerEvents(new OnItemSwap(), this);
        this.getServer().getPluginManager().registerEvents(new OnHotbarSwap(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
