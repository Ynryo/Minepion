package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndDrawController {
    private GameController gameController;
    
    private Player player1;
    private Player player2;

    @FXML
    private Button leaveGameBtn;  // Bouton pour quitter le jeu

    @FXML
    private Button restart_game_btn;  // Bouton pour redémarrer une nouvelle partie

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
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
    void restartGame(ActionEvent event) throws IOException {
        ((Stage) restart_game_btn.getScene().getWindow()).close();

        if (gameController != null) {
            gameController.initialize();
            gameController.setPlayers(player1, player2, gameController.getOriginalStarter());
        }
    }
}