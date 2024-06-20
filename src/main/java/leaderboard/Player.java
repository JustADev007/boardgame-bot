package leaderboard;

import java.util.ArrayList;

public class Player {
    private String playerName;
   private ArrayList<Win> wins;

   public Player(String name){
       this.playerName = name;
       this.wins = new ArrayList<>();
   }

   public String getPlayerName(){
       return this.playerName;
   }



   public void addWin(String gameName){
       this.wins.add(new Win(gameName));
   }

   public int getTotalWins(){
       return wins.size();
   }

   public String getWinsByGame(String gameName){
       int gameWin = 0;

       for(Win game: wins){
           if(game.getGameName().equals(gameName)){
               gameWin++;
           }
       }
       if(gameWin == 0){
           return "";
       }
       return playerName + ":       " + gameWin + " wins!";
   }

    public boolean equals(Object player){

        if(this == player){
            return true;
        }

        if(!(player instanceof Player) ){
            return false;
        }

        Player newPlayer = (Player) player;

        return this.playerName.equals(newPlayer.playerName);


    }
}
