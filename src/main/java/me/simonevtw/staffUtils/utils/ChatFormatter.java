package me.simonevtw.staffUtils.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public final class ChatFormatter {

    private static final String PREFIX = "\uE90B ";

    private static final MiniMessage sanitizer = MiniMessage.builder()
            .tags(TagResolver.builder()
                    .resolver(StandardTags.color())
                    .resolver(StandardTags.decorations())
                    .resolver(StandardTags.rainbow())
                    .resolver(StandardTags.gradient())
                    .resolver(StandardTags.reset())
                    .build())
            .postProcessor(c -> c.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build();

    private static final MiniMessage internal = MiniMessage.builder()
            .postProcessor(c -> c.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .build();

    private ChatFormatter() {
    }

    public static Component getTpMessage(String name) {
        Component hover = internal.deserialize("<green>Clicca per teletrasportarti dal giocatore</green>");
        return internal.deserialize("<green>[Clicca qui per teletrasportarti]</green>")
                .hoverEvent(HoverEvent.showText(hover))
                .clickEvent(ClickEvent.runCommand("/tp " + name));
    }

    public static Component helpMessage(String playerName, String message) {
        return internal.deserialize(
                "<red><player></red> ha inviato una richiesta di aiuto:\n<white><message></white>",
                Placeholder.unparsed("player", playerName),
                Placeholder.unparsed("message", message));
    }

    public static Component serverMessage(String text, boolean unsafe) {
        return unsafe ? internal.deserialize(PREFIX + text) : deserialize(PREFIX + text);
    }

    public static Component serverMessage(String text) {
        return serverMessage(text, false);
    }

    public static Component deserialize(String str) {
        return sanitizer.deserialize(str);
    }

}