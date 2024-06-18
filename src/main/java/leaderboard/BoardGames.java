package leaderboard;

import java.util.ArrayList;

public class BoardGames {
    public ArrayList<Game> games;

    public BoardGames(){
        this.games = new ArrayList<>();
    }

    public void addGame(Game game) {
        if(games.contains(game)){
            return;
        }
        games.add(game);
    }

    public ArrayList<Game> getList(){
        return this.games;
    }
    public boolean contains(Game game){
        return this.games.contains(game);

    }


    public String toString(){
        String str = "";
        int index = 1;
        if(games.size()>1) {
            for (Game game : games) {
                str += index + ": " + game + "\n";
                index++;
            }

            return str;
        }
        return "There are no games in list!";

    }
}
