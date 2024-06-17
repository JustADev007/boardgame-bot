package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotCommands extends ListenerAdapter {


    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("hello")) event.reply("hello").queue();
    }
}