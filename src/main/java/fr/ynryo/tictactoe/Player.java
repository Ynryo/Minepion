package fr.ynryo.tictactoe;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * Classe représentant un joueur dans le jeu MinePion
 * Gère les données du joueur (nom, score, symbole) et assure leur persistance via JsonManipulator
 */
public class Player implements Serializable {
    
    //-------------------------------------------------------------------------
    // Attributs
    //-------------------------------------------------------------------------
    
    /** Nom du joueur */
    private String name;
    
    /** Score du joueur */
    private int score;
    
    /** Nombre de parties jouées */
    private int nbGamePlayed = 0;
    
    /** Symbole choisi par le joueur (X ou O généralement) */
    private String symbol;
    
    /** Gestionnaire de persistance pour les données du joueur */
    private final JsonManipulator jsonManipulator = new JsonManipulator(
            "src/main/resources/fr/ynryo/tictactoe/json/player_data.json");

    //-------------------------------------------------------------------------
    // Constructeurs
    //-------------------------------------------------------------------------
    
    /**
     * Crée un joueur avec un nom spécifié
     * Charge le symbole et le score depuis le fichier de sauvegarde
     * 
     * @param name Nom du joueur
     */
    public Player(String name) {
        this.name = name;
        this.symbol = (String) jsonManipulator.read(name, "symbol");
        this.score = (int) jsonManipulator.read(name, "score");
    }

    /**
     * Crée un joueur avec un nom et un symbole spécifiés
     * Charge le score depuis le fichier de sauvegarde et sauvegarde le symbole
     * 
     * @param name Nom du joueur
     * @param symbol Symbole choisi par le joueur
     */
    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = (int) jsonManipulator.read(name, "score");
        jsonManipulator.write(name, "symbol", symbol);
    }

    //-------------------------------------------------------------------------
    // Getters et setters
    //-------------------------------------------------------------------------
    
    /**
     * @return Le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom du joueur
     * 
     * @param name Nouveau nom du joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Le score actuel du joueur en mémoire
     */
    public int getScore() {
        return score;
    }

    /**
     * @return Le score du joueur tel que stocké dans le fichier
     */
    public int getScoreInFile() {
        return (int) jsonManipulator.read(name, "score");
    }

    /**
     * Modifie le score du joueur et sauvegarde la modification
     * 
     * @param score Nouveau score du joueur
     */
    public void setScore(int score) {
        this.score = score;
        jsonManipulator.write(this.name, "score", this.score);
    }

    /**
     * @return Le symbole actuel du joueur en mémoire
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return Le symbole du joueur tel que stocké dans le fichier
     */
    public String getSymbolInFile() {
        return (String) jsonManipulator.read(name, "symbol");
    }

    /**
     * Modifie le symbole du joueur et sauvegarde la modification
     * 
     * @param symbol Nouveau symbole du joueur
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
        jsonManipulator.write(this.name, "symbol", this.symbol);
    }

    /**
     * @return Le nombre de parties jouées
     */
    public int getNbGamePlayed() {
        return nbGamePlayed;
    }

    //-------------------------------------------------------------------------
    // Méthodes de gestion du score et des statistiques
    //-------------------------------------------------------------------------
    
    /**
     * Incrémente le compteur de parties jouées
     */
    public void increaseNbGamePlayed() {
        this.nbGamePlayed++;
    }

    /**
     * Incrémente le score du joueur de 1 et sauvegarde la modification
     */
    public void increaseScore() {
        this.score++;
        jsonManipulator.write(this.name, "score", this.score);
    }

    /**
     * Incrémente le score du joueur d'un montant spécifié et sauvegarde la modification
     * 
     * @param amount Montant à ajouter au score
     */
    public void increaseScore(int amount) {
        this.score += amount;
        jsonManipulator.write(this.name, "score", this.score);
    }
    
    /**
     * Représentation textuelle du joueur
     * 
     * @return Chaîne de caractères décrivant le joueur
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", nbGamePlayed=" + nbGamePlayed +
                '}';
    }
}