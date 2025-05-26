package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Contrôleur pour la fenêtre modale des règles du jeu
 * Gère l'affichage des règles et la possibilité de fermer cette fenêtre
 */
public class RulesController {

    /**
     * Bouton permettant de fermer la fenêtre des règles
     * Injecté depuis le fichier FXML associé
     */
    @FXML
    private Button close_btn;

    /**
     * Gestionnaire d'événement pour le bouton de fermeture
     * Ferme la fenêtre modale des règles lorsque l'utilisateur clique sur le bouton
     *
     * @param event L'événement de clic sur le bouton de fermeture
     */
    @FXML
    void closeModal(ActionEvent event) {
        // Récupérer la fenêtre modale actuelle à partir du bouton
        Stage modalStage = (Stage) close_btn.getScene().getWindow();
        
        // Fermer la fenêtre modale
        modalStage.close();
    }
}