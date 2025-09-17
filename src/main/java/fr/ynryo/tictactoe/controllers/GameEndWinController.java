package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndWinController {
    private Player currentPlayer;  // Joueur gagnant
    private Player otherPlayer;    // Joueur perdant
    
    private GameController gameController;

    @FXML
    private Button leaveGameBtn;  // Bouton pour quitter le jeu complètement

    @FXML
    private Label player1_name;  // Nom du joueur gagnant

    @FXML
    private Label player1_score;  // Score du joueur gagnant

    @FXML
    private Label player2_name;  // Nom du joueur perdant

    @FXML
    private Label player2_score;  // Score du joueur perdant

    @FXML
    private Button restart_game_btn;  // Bouton pour redémarrer une nouvelle partie

    @FXML
    private Label winnerName;  // Nom du vainqueur affiché en évidence

    public void setCurrentPlayer(Player winner, Player loser) {
        this.currentPlayer = winner;
        this.otherPlayer = loser;
        
        // Mettre à jour les labels avec les informations des joueurs
        winnerName.setText(winner.getName());
        player1_name.setText(winner.getName());
        player1_score.setText(winner.getScore() + " points");
        player2_name.setText(loser.getName());
        player2_score.setText(loser.getScore() + " points");
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
            gameController.setPlayers(currentPlayer, otherPlayer, gameController.getOriginalStarter());
        }
    }
}