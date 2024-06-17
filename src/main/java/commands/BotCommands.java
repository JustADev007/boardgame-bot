package commands;

import leaderboard.BoardGames;
import leaderboard.Game;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotCommands extends ListenerAdapter {
    BoardGames boardGames = new BoardGames();

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) event.reply("hello").queue();

        else if (event.getName().equals("add-game")) {

            String name = event.getOption("name").getAsString();
            String weight = event.getOption("weight").getAsString();
            int minPlayers = event.getOption("min-players").getAsInt();
            int maxPlayers = event.getOption("max-players").getAsInt();

            Game game = new Game(name, weight, minPlayers, maxPlayers);

            if (boardGames.getList().contains(game)) {

                event.reply("Game is already in list!").queue();
                return;
            }

            boardGames.addGame(game);

            event.reply("Added " + name + " to the list!").queue();

        }
    }
}
