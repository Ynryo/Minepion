package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameEndDrawController {

    @FXML
    private Button leaveGameBtn;

    @FXML
    private Button restart_game_btn;

    private GameController gameController;

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
