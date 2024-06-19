package commands;

import leaderboard.BoardGames;
import leaderboard.Game;
import leaderboard.Player;
import leaderboard.PlayerList;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotCommands extends ListenerAdapter {
    private final BoardGames boardGames;
    private final PlayerList playerList;

    public BotCommands() {
        this.boardGames = new BoardGames();
        this.playerList = new PlayerList();
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
            else if(event.getName().equals("add-player")) {

            event.deferReply().queue();

            String name = event.getOption("name").getAsString();

            Player player = new Player(name);

            if(playerList.contains(player)){
                event.getHook().sendMessage(name + " has already been added").queue();
                return;
            }


                 playerList.addPlayer(name);

            event.getHook().sendMessage("Added " + name + " to the list").queue();
        }
    }
}
