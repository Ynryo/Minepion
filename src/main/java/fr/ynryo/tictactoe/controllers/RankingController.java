package fr.ynryo.tictactoe.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Contrôleur pour la page de classement (ranking) du jeu
 */
public class RankingController implements Initializable {

    @FXML
    private Button back_btn;

    @FXML
    private TableView<PlayerRanking> tableView;

    @FXML
    private TableColumn<PlayerRanking, String> pseudoColumn;

    @FXML
    private TableColumn<PlayerRanking, Integer> nbPartiesColumn;

    @FXML
    private TableColumn<PlayerRanking, Integer> scoreColumn;

    private ObservableList<PlayerRanking> playerData = FXCollections.observableArrayList();

    /**
     * Classe pour stocker les données des joueurs
     */
    public static class PlayerRanking {
        private String pseudo;
        private int nbParties;
        private int score;

        public PlayerRanking(String pseudo, int nbParties, int score) {
            this.pseudo = pseudo;
            this.nbParties = nbParties;
            this.score = score;
        }

        // Getters et setters pour le binding avec TableView
        public String getPseudo() { return pseudo; }
        public void setPseudo(String pseudo) { this.pseudo = pseudo; }

        public int getNbParties() { return nbParties; }
        public void setNbParties(int nbParties) { this.nbParties = nbParties; }

        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du RankingController...");

        // Configuration des colonnes
        pseudoColumn.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        nbPartiesColumn.setCellValueFactory(new PropertyValueFactory<>("nbParties"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Chargement des données
        loadPlayerData();

        // Affichage dans le tableau
        tableView.setItems(playerData);

        // Tri par score décroissant
        tableView.getSortOrder().add(scoreColumn);
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableView.sort();

        System.out.println("Nombre de joueurs chargés: " + playerData.size());
    }

    /**
     * Charge les données des joueurs depuis le fichier JSON
     */
    private void loadPlayerData() {
        try {
            // Charger le fichier JSON depuis les ressources
            InputStream is = getClass().getResourceAsStream("/fr/ynryo/tictactoe/json/player_data.json");
            if (is == null) {
                System.err.println("Fichier JSON introuvable!");
                return;
            }

            // Lire le contenu JSON
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
            Map<String, Map<String, Object>> data = gson.fromJson(new InputStreamReader(is), type);

            if (data != null) {
                System.out.println("Données JSON chargées avec succès: " + data.size() + " joueurs");

                for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
                    String name = entry.getKey();

                    // Ignorer les entrées sans nom
                    if (name == null || name.isEmpty()) {
                        System.out.println("Ignoré un joueur sans nom");
                        continue;
                    }

                    Map<String, Object> playerInfo = entry.getValue();

                    // Récupérer le score
                    int score = 0;
                    if (playerInfo.containsKey("score")) {
                        Object scoreObj = playerInfo.get("score");
                        if (scoreObj instanceof Number) {
                            score = ((Number) scoreObj).intValue();
                        } else if (scoreObj instanceof String) {
                            try {
                                score = Integer.parseInt((String) scoreObj);
                            } catch (NumberFormatException e) {
                                System.err.println("Score non valide pour " + name + ": " + scoreObj);
                            }
                        }
                    }

                    // Ajouter à la liste (avec nbParties à 0 car non disponible)
                    PlayerRanking player = new PlayerRanking(name, 0, score);
                    playerData.add(player);
                    System.out.println("Joueur ajouté: " + name + " (Score: " + score + ")");
                }
            } else {
                System.err.println("Aucune donnée trouvée dans le fichier JSON");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des données: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void toBack(ActionEvent event) {
        try {
            // Récupérer la fenêtre actuelle
            Stage stage = (Stage) back_btn.getScene().getWindow();

            // Charger le fichier FXML de la page d'accueil
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/starting_page.fxml"));

            // Obtenir les dimensions de l'écran pour un affichage optimal
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

            // Créer une nouvelle scène avec la page d'accueil
            Scene scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());

            // Appliquer les styles CSS à la scène
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());

            // Configurer et afficher la fenêtre avec la nouvelle scène
            stage.setScene(scene);
            stage.setTitle("MinePion - Configuration de la partie");
            stage.setMaximized(true);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ouverture de la fenêtre de configuration de la partie:");
            e.printStackTrace();
        }
    }
}