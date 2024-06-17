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

    public String toString() {
        return this.name;
    }
}
