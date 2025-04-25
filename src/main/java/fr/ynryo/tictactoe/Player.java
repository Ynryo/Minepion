package fr.ynryo.tictactoe;

public class Player {
    private String name;
    private int score;
    private int nbGamePlayed = 0;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNbGamePlayed() {
        return nbGamePlayed;
    }

    public void increaseNbGamePlayed() {
        this.nbGamePlayed++;
    }
    
    public void increaseScore(int amount) {
        this.score += amount;
    }

}
