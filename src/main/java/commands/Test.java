package commands;

import leaderboard.BoardGames;
import leaderboard.Game;

public class Test {
    public static void main(String[] args) {
        BoardGames games = new BoardGames();
        games.addGame(new Game("Gloomhaven", "heavy", 1,4));
        games.addGame(new Game("Too Many Bones", "heavy", 1,4));
        games.addGame(new Game("Mage Knight", "heavy", 1,4));
        games.addGame(new Game("azul", "light", 2,4));

        System.out.println(games.getRandom());

        System.out.println(games.getSuggestedGames("heavy",6));




        System.out.println(games);

    }
}
