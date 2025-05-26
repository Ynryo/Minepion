package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur gérant la fenêtre de fin de partie lorsqu'un joueur gagne
 * Affiche le vainqueur, les scores des deux joueurs et propose les options de fin de partie
 */
public class GameEndWinController {
    // Références aux joueurs
    private Player currentPlayer;  // Joueur gagnant
    private Player otherPlayer;    // Joueur perdant
    
    // Référence au contrôleur principal de jeu pour pouvoir réinitialiser la partie
    private GameController gameController;

    // Éléments d'interface utilisateur injectés depuis le fichier FXML
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

    /**
     * Configure l'interface avec les informations des joueurs
     * Définit le gagnant et le perdant, et met à jour l'affichage des scores
     *
     * @param winner Le joueur qui a gagné la partie
     * @param loser Le joueur qui a perdu la partie
     */
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
    
    /**
     * Définit le contrôleur de jeu principal
     * Cette référence permet d'interagir avec le jeu principal en cas de redémarrage
     *
     * @param gameController Le contrôleur principal de jeu
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Gestionnaire d'événement pour le bouton "Quitter le jeu"
     * Ferme à la fois la fenêtre modale de fin de partie et la fenêtre principale du jeu
     *
     * @param event L'événement de clic sur le bouton
     */
    @FXML
    void leaveGame(ActionEvent event) {
        // Récupérer la fenêtre modale actuelle
        Stage modalStage = (Stage) leaveGameBtn.getScene().getWindow();
        // Récupérer la fenêtre principale (propriétaire de la fenêtre modale)
        Stage mainStage = (Stage) modalStage.getOwner();
        // Fermer les deux fenêtres
        modalStage.close();
        mainStage.close();
    }

    /**
     * Gestionnaire d'événement pour le bouton "Recommencer une partie"
     * Ferme la fenêtre modale et réinitialise le jeu principal pour une nouvelle partie
     *
     * @param event L'événement de clic sur le bouton
     * @throws IOException Si une erreur survient lors de la réinitialisation du jeu
     */
    @FXML
    void restartGame(ActionEvent event) throws IOException {
        // Fermer la fenêtre modale de fin de partie
        ((Stage) restart_game_btn.getScene().getWindow()).close();

        // Si le contrôleur principal existe, réinitialiser le jeu
        if (gameController != null) {
            // Réinitialiser l'interface du jeu
            gameController.initialize();
            // Réinitialiser les joueurs en utilisant le joueur qui avait commencé à l'origine
            gameController.setPlayers(currentPlayer, otherPlayer, gameController.getOriginalStarter());
        }
    }
}