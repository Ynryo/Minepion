package fr.ynryo.tictactoe;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class Player implements Serializable {
    private String name;
    private int score;
    private int nbGamePlayed = 0;
    private String symbol;
    private JsonManipulator jsonManipulator = new JsonManipulator("src/main/resources/fr/ynryo/tictactoe/json/player_data.json");

    public Player(String symbol) {
        this.symbol = symbol;
        jsonManipulator.readScore(name);
    }

    public Player(String symbol, String name) {
        this(symbol);
        this.name = name;
        this.score = jsonManipulator.readScore(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.score = jsonManipulator.readScore(name);
    }

    public int getScore() {
        return score;
    }

    public int getScoreInFile() {
        return jsonManipulator.readScore(name);
    }

    public void setScore(int score) {
        this.score = score;
        jsonManipulator.writeScore(this, this.score);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNbGamePlayed() {
        return nbGamePlayed;
    }

    public void increaseNbGamePlayed() {
        this.nbGamePlayed++;
    }

    private void saveScoreToJSON() {

    }

    public void increaseScore() {
        this.score++;
        jsonManipulator.writeScore(this, this.score);
    }

    public void increaseScore(int amount) {
        this.score += amount;
        jsonManipulator.writeScore(this, this.score);
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