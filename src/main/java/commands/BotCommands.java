package commands;

import leaderboard.BoardGames;
import leaderboard.Game;
import leaderboard.Player;
import leaderboard.PlayerList;
import net.dv8tion.jda.api.EmbedBuilder;
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

        playerList.addPlayer("Justin");
        playerList.addPlayer("Tre");
        playerList.addPlayer("Kevin");

        playerList.addWin("Justin", "Azul");
        playerList.addWin("Justin", "Azul");
        playerList.addWin("Justin", "Azul");
        playerList.addWin("Kevin" , "Deception");


    }

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) event.reply("hello").queue();

        else if (event.getName().equals("add-game")) {

            String name = event.getOption("name").getAsString();
            String weight = event.getOption("weight").getAsString();
            int minPlayers = event.getOption("min-players").getAsInt();
            int maxPlayers = event.getOption("max-players").getAsInt();

            Game game = new Game(name, weight, minPlayers, maxPlayers);

            event.deferReply().queue();

            if (boardGames.contains(game)) {
                EmbedBuilder response = new EmbedBuilder();
                response.setTitle("🛑 Nope");
                response.setDescription("Cannot add: " + name + " has already been added");
                response.setColor(0xF50B0B);
                response.setFooter(event.getMember().getUser().getName() + " you should have known better!",event.getMember().getUser().getAvatarUrl());


                event.getHook().sendMessageEmbeds(response.build()).queue();
                response.clear();
                return;
            }



            boardGames.addGame(game);

            System.out.println(boardGames);

            EmbedBuilder response = new EmbedBuilder();
            response.setTitle("✅ Success");
            response.setDescription("Added " + name + " to the list!");
            response.setColor(0x28ED73);
            response.setFooter("Added by " + event.getMember().getUser().getName(),event.getMember().getUser().getAvatarUrl());


            event.getHook().sendMessageEmbeds(response.build()).queue();
            response.clear();
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
        else if(event.getName().equals("leaderboard")) {

            event.deferReply().queue();

            if(event.getOption("game-name") == null){
                event.getHook().sendMessage(playerList.getLeaderboard()).queue();
                return;
            }

            String gameName = event.getOption("game-name").getAsString();

            event.getHook().sendMessage(playerList.getWinsByGame(gameName)).queue();
        }
        else if(event.getName().equals("add-win")) {

            event.deferReply().queue();

            String playerName = event.getOption("player-name").getAsString();
            String gameName = event.getOption("game-name").getAsString();

            playerList.addWin(playerName,gameName);

            event.getHook().sendMessage("Congrats " + playerName + "!" ).queue();
        }
    }
}
