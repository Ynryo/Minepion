package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur gérant la fenêtre de fin de partie en cas d'égalité (match nul)
 * Gère les actions possibles après un match nul : quitter le jeu ou recommencer une partie
 */
public class GameEndDrawController {
    // Référence au contrôleur principal de jeu pour pouvoir interagir avec lui
    private GameController gameController;
    
    // Références aux objets joueurs pour les réutiliser en cas de redémarrage
    private Player player1;
    private Player player2;

    // Boutons de l'interface utilisateur annotés avec @FXML pour l'injection depuis le fichier FXML
    @FXML
    private Button leaveGameBtn;  // Bouton pour quitter le jeu

    @FXML
    private Button restart_game_btn;  // Bouton pour redémarrer une nouvelle partie

    /**
     * Définit les joueurs pour cette fenêtre de fin de partie
     * Ces références sont nécessaires pour pouvoir redémarrer une partie avec les mêmes joueurs
     * 
     * @param player1 Le premier joueur
     * @param player2 Le second joueur
     */
    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
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
     * Ferme la fenêtre modale ainsi que la fenêtre principale du jeu
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
     * Ferme la fenêtre modale et réinitialise le jeu principal
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
            gameController.setPlayers(player1, player2, gameController.getOriginalStarter());
        }
    }
}