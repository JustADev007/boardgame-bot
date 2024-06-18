package commands;

import leaderboard.BoardGames;
import leaderboard.Game;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotCommands extends ListenerAdapter {
    private final BoardGames boardGames;

    public BotCommands() {
        this.boardGames = new BoardGames();
        boardGames.addGame(new Game("Gloomhaven", "heavy", 1,4));
        boardGames.addGame(new Game("Too Many Bones", "heavy", 1,4));
        boardGames.addGame(new Game("Mage Knight", "heavy", 1,4));
        boardGames.addGame(new Game("Azul", "light", 2,4));

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

            if (boardGames.contains(game)) {
                event.reply("Game is already in list!").queue();
                return;
            }



            boardGames.addGame(game);

            System.out.println(boardGames);

            event.reply("Added " + name + " to the list!").queue();

        }
            else if (event.getName().equals("games")) {

            event.deferReply().queue();

            String gameList = boardGames.toString();
            System.out.println(gameList);

            event.getHook().sendMessage(gameList).queue();

        }
            else if (event.getName().equals("random")) {

            event.deferReply().queue();

            String randomGame = boardGames.getRandom();

            event.getHook().sendMessage("The game i picked is " + randomGame).queue();

        }
            else if(event.getName().equals("suggest-games")) {

            event.deferReply().queue();

            String weight = event.getOption("weight").getAsString();
            int minPlayers = event.getOption("player-count").getAsInt();

            String suggestedGames = boardGames.getSuggestedGames(weight,minPlayers);

            event.getHook().sendMessage(suggestedGames).queue();
        }
    }
}
