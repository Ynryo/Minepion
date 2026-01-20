package fr.ynryo.tictactoe;

import java.io.Serializable;

public class Player implements Serializable {

    /** Nom du joueur */
    private String name;
    
    /** Score du joueur */
    private int score;
    
    /** Nombre de parties jouées */
    private int nbGamePlayed = 0;
    
    private String symbol;
    
    private final JsonManipulator jsonManipulator = new JsonManipulator(
            "src/main/resources/fr/ynryo/tictactoe/json/player_data.json");

    public Player(String name) {
        this.name = name;
        this.symbol = (String) jsonManipulator.read(name, "symbol");
        this.score = (int) jsonManipulator.read(name, "score");
    }

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = (int) jsonManipulator.read(name, "score");
        jsonManipulator.write(name, "symbol", symbol);
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

    public int getScoreInFile() {
        return (int) jsonManipulator.read(name, "score");
    }

    public void setScore(int score) {
        this.score = score;
        jsonManipulator.write(this.name, "score", this.score);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSymbolInFile() {
        return (String) jsonManipulator.read(name, "symbol");
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        jsonManipulator.write(this.name, "symbol", this.symbol);
    }

    public int getNbGamePlayed() {
        return nbGamePlayed;
    }

    public void increaseNbGamePlayed() {
        this.nbGamePlayed++;
    }

    public void increaseScore() {
        this.score++;
        jsonManipulator.write(this.name, "score", this.score);
    }

    public void increaseScore(int amount) {
        this.score += amount;
        jsonManipulator.write(this.name, "score", this.score);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", nbGamePlayed=" + nbGamePlayed +
                '}';
    }
}