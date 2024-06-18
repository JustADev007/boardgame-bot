package leaderboard;

public class Game {
    private String name;
    private String weight;
    private int minPlayers;
    private int maxPlayers;

    public Game(String name, String weight, int minPlayers, int maxPlayers){
        this.name = name;
        this.weight = weight;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public String getName(){
        return this.name;
    }

    public String getWeight(){
        return this.weight;
    }

    public boolean validPlayerCount(int numOfPlayers){
        return numOfPlayers >= minPlayers && numOfPlayers <= maxPlayers;
    }

    public boolean equals(Object game){

        if(this == game){
            return true;
        }

        if(!(game instanceof Game) ){
            return false;
        }

        Game comparedGame = (Game) game;

        return this.name.equals(comparedGame.name);


    }

    public String toString() {
        return this.name;
    }
}
