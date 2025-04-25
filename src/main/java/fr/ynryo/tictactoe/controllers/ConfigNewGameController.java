package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigNewGameController {

    @FXML
    private TextField input_player1_name;

    @FXML
    private TextField input_player2_name;

    @FXML
    private Button one_player_mode;

    @FXML
    private Button start_game_btn;

    @FXML
    private Button two_players_mode;

    private int nbPlayerMode;
    private String player1_name;
    private String player2_name;

    public int getNbPlayerMode() {
        return nbPlayerMode;
    }

    public void setNbPlayerMode(int nbPlayerMode) {
        this.nbPlayerMode = nbPlayerMode;
    }

    @FXML
    void onePlayerSelected(ActionEvent event) {
        setNbPlayerMode(1);
    }

    @FXML
    void twoPlayersSelected(ActionEvent event) {
        setNbPlayerMode(2);
    }

    @FXML
    public void initialize() {
        one_player_mode.setDisable(true);
    }

    @FXML
    void startGame(ActionEvent event) {
        String player1Name = input_player1_name.getText();
        String player2Name = input_player2_name.getText();
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        IA ia = null;

        switch (getNbPlayerMode()) {
            case 1:
                ia = new IA();
                // Ici, tu pourrais stocker player1 et ia pour lancer une partie solo
                break;
            case 2:
                try {
                    Stage modalStage = (Stage) two_players_mode.getScene().getWindow();
                    modalStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/game.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage mainStage = (Stage) modalStage.getOwner();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.setMaximized(false);
                    mainStage.setMaximized(true);
                    mainStage.show();
                } catch (Exception e) {
                    System.err.println("Erreur lors du lancement de la partie en mode 2 joueurs :");
                    e.printStackTrace();
                }
                break;
            default:
                // Gérer un cas inattendu ou prévenir l’utilisateur
                return;
        }
    }
}