package me.simonevtw.staffUtils.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.UUID;

public class FreezeCommand {

   public static final HashSet<UUID> frozenPlayers = new HashSet<>();

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("freeze")
                .then(Commands.argument("target", ArgumentTypes.player())
                        .executes(context -> {
                            var source = context.getSource();


                            if (!(source.getSender() instanceof Player p)) {
                                source.getSender().sendMessage("Solo i giocatori possono usare questo comando!");
                                return 0;
                            }

                            PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
                            var resolvedPlayers = resolver.resolve(source);
                            Player target = resolvedPlayers.iterator().next();

                            if (target.equals(p)) {
                                p.sendRichMessage("<red>Non puoi usare il freeze su tè stesso");
                                return 0;
                            }

                            if (!(p.hasPermission("freeze.use"))) {
                                p.sendRichMessage("<red>Permessi insufficienti.");
                                return 0;
                            }

                            if(frozenPlayers.contains(target.getUniqueId())) {
                                p.sendRichMessage("<aqua>Hai scongelato <blue>" + target.getName());
                                target.sendRichMessage("<aqua>Sei stato scongelato");
                                frozenPlayers.remove(target.getUniqueId());
                                target.setGlowing(false);
                            }else{
                                p.sendRichMessage("<aqua>Hai congelato <blue>" + target.getName());
                                target.sendRichMessage("<aqua>Sei stato congelato");
                                frozenPlayers.add(target.getUniqueId());
                                target.setGlowing(true);
                            }

                            return 1;
                        }))
                .build();
    }
}
