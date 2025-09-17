package fr.ynryo.tictactoe.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.ynryo.tictactoe.stageManager.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

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

    public static class PlayerRanking {
        private String pseudo;
        private int nbParties;
        private int score;

        public PlayerRanking(String pseudo, int nbParties, int score) {
            this.pseudo = pseudo;
            this.nbParties = nbParties;
            this.score = score;
        }

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

        tableView.setItems(playerData);

        tableView.getSortOrder().add(scoreColumn);
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableView.sort();

        System.out.println("Nombre de joueurs chargés: " + playerData.size());
    }

    private void loadPlayerData() {
        try {
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
        StageManager stageManager = new StageManager(
                "MinePion - Configuration de la partie",
                "/fr/ynryo/tictactoe/fxml/starting_page.fxml",
                (Stage) back_btn.getScene().getWindow()
        );
        stageManager.switchStage();
    }
}