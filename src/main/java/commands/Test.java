package commands;

import leaderboard.BoardGames;
import leaderboard.Game;
import leaderboard.PlayerList;

public class Test {
    public static void main(String[] args) {
        BoardGames games = new BoardGames();
        PlayerList players = new PlayerList();
        games.addGame(new Game("Gloomhaven", "heavy", 1,4));
        games.addGame(new Game("Too Many Bones", "heavy", 1,4));
        games.addGame(new Game("Mage Knight", "heavy", 1,4));
        games.addGame(new Game("azul", "light", 2,4));

        System.out.println(games.getRandom());


    players.addPlayer("Justin");
        players.addPlayer("Tre");
        players.addPlayer("Kevin");

        players.addWin("Justin", "Azul");
        players.addWin("Justin", "Azul");
        players.addWin("Justin", "Azul");
        players.addWin("Kevin" , "Deception");

        System.out.println(players.getLeaderboard());
        System.out.println(players.getWinsByGame("Azul"));
        System.out.println(players.getWinsByGame("Deception"));







    }
}
