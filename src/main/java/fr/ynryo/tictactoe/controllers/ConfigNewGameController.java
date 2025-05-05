package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigNewGameController {

    @FXML
    private TextField input_player1_name;

    @FXML
    private TextField input_player1_symbol;

    @FXML
    private TextField input_player2_name;

    @FXML
    private TextField input_player2_symbol;

    @FXML
    private Button one_player_mode;

    @FXML
    private RadioButton radioStartP1;

    @FXML
    private RadioButton radioStartP2;

    @FXML
    private Button start_game_btn;

    @FXML
    private Button two_players_mode;

    private int nbPlayerMode = 2;
    public Player player1;
    public Player player2;

    @FXML
    void initialize() {
        one_player_mode.setDisable(true);
        nbPlayerMode = 2;
    }

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
    void startGame(ActionEvent event) {
        player1 = new Player(input_player1_symbol.getText(), input_player1_name.getText());
        player2 = new Player(input_player2_symbol.getText(), input_player2_name.getText());
        IA ia = null;

        switch (getNbPlayerMode()) {
            case 1:
                ia = new IA();
                // Ici, tu pourrais stocker player1 et ia pour lancer une partie solo
                break;
            case 2:
                try {
                    Stage modalStage = (Stage) two_players_mode.getScene().getWindow();
                    Stage mainStage = (Stage) modalStage.getOwner();
                    modalStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/game.fxml"));
                    Parent root = fxmlLoader.load();

                    GameController gameController = fxmlLoader.getController();
                    gameController.setPlayers(player1, player2, whoStart());

                    mainStage.setScene(new Scene(root));
                    mainStage.show();
                } catch (Exception e) {
                    System.err.println("Erreur lors du lancement de la partie en mode 2 joueurs :");
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Sélectionnez un mode entre 1 ou 2 joueurs.");
                return;
        }
    }

    private Player whoStart() {
        if (radioStartP1.isSelected()) {
            return player1;
        } else if (radioStartP2.isSelected()) {
            return player2;
        } else {
            return null;
        }
    }
}