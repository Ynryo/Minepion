package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndWinController {

    private Player currentPlayer;
    // Dans GameEndWinController
    private GameController gameController;

    @FXML
    private Button leaveGameBtn;

    @FXML
    private Button restart_game_btn;

    @FXML
    private Label winnerName;

    @FXML
    void initialize() {
        winnerName.setText(""); // Optionnel : met un texte par défaut
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        if (winnerName != null && currentPlayer != null) {
            winnerName.setText(currentPlayer.getName());
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    void leaveGame(ActionEvent event) {
        Stage modalStage = (Stage) leaveGameBtn.getScene().getWindow();
        Stage mainStage = (Stage) modalStage.getOwner();
        modalStage.close();
        mainStage.close();
    }

    @FXML
    void restartGame(ActionEvent event) {
        if (gameController != null) {
            gameController.resetGame(new ActionEvent()); // Réinitialise les boutons affichés
        }
        ((Stage) restart_game_btn.getScene().getWindow()).close();
    }
}