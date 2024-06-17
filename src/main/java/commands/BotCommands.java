package commands;

import leaderboard.BoardGames;
import leaderboard.Game;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotCommands extends ListenerAdapter {
    private BoardGames boardGames;

    public BotCommands() {
        this.boardGames = new BoardGames();

    }

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) event.reply("hello").queue();

        else if (event.getName().equals("add-game")) {

            String name = event.getOption("name").getAsString();
            String weight = event.getOption("weight").getAsString();
            int minPlayers = event.getOption("min-players").getAsInt();
            int maxPlayers = event.getOption("max-players").getAsInt();

            Game game = new Game(name, weight, minPlayers, maxPlayers);
            System.out.println(game);
            System.out.println(game.getWeight());
            System.out.println(boardGames);

            if (boardGames.contains(name)) {

                event.reply("Game is already in list!").queue();
                return;
            }
//
//            boardGames.addGame(game);

            event.reply("Added " + name + " to the list!").queue();

        } else if (event.getName().equals("games")) {
            String gameList = boardGames.toString();
            System.out.println(gameList);

            event.reply(gameList).queue();
        }
    }
}
