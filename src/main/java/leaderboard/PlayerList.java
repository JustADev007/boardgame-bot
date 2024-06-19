package leaderboard;

import java.util.ArrayList;

public class PlayerList {
    private ArrayList<Player> players;

    public PlayerList(){
        this.players = new ArrayList<>();
    }

    public void addPlayer(String playerName){
        Player newPlayer = new Player(playerName);
        if(players.contains(newPlayer)){
            return;
        }
        players.add(newPlayer);
    }

    public void addWin(String playerName, String gameName){
        for(Player player: players){
            if(player.getPlayerName().equals(playerName)){
                player.addWin(gameName);
            }
        }
    }

    public String getWinsByGame(String gameName){
        String gameWins = "";
        for(Player player:players){
            if(player.getWinsByGame(gameName).isEmpty()){
                continue;
            }
          gameWins += player.getWinsByGame(gameName) + "\n";
        }
        return  gameWins;
    }

    public String getLeaderboard(){
        String leaderboard = "";
        for(Player player: players){
            leaderboard += player.getPlayerName() + ": " + player.getTotalWins() + " wins! \n";
        }
        return leaderboard;
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public boolean contains(Player player){
        return this.players.contains(player);
    }



}
