package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Contrôleur gérant la fenêtre de confirmation pour quitter une partie en cours
 * Offre à l'utilisateur les options de confirmer ou d'annuler son action de sortie
 */
public class CloseGameConfirmController {

    // Boutons de l'interface utilisateur injectés depuis le fichier FXML
    @FXML
    private Button confirmBtn;  // Bouton pour confirmer la sortie de la partie

    @FXML
    private Button leaveBtn;    // Bouton pour annuler et rester dans la partie en cours

    /**
     * Gestionnaire d'événement pour la confirmation de fermeture du jeu
     * Ferme la partie en cours et retourne à la page d'accueil du jeu
     *
     * @param event L'événement de clic sur le bouton de confirmation
     */
    @FXML
    void closeGame(ActionEvent event) {
        try {
            // Récupérer la fenêtre modale actuelle
            Stage modalStage = (Stage) confirmBtn.getScene().getWindow();
            
            // Fermer la fenêtre modale
            closeModal(null);
            
            // Récupérer la fenêtre principale du jeu (propriétaire de la fenêtre modale)
            Stage mainStage = (Stage) modalStage.getOwner();

            // Charger la page d'accueil
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/starting_page.fxml"));
            
            // Obtenir les dimensions de l'écran pour un affichage optimal
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            
            // Créer une nouvelle scène avec la page d'accueil chargée
            Scene scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
            
            // Appliquer les styles CSS
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());

            // Configurer la fenêtre principale
            mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
            mainStage.setTitle("MinePion");
            mainStage.setMaximized(true);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            // Gestion des erreurs potentielles lors du chargement de la page d'accueil
            System.err.println("Error while closing game and returning to start page:");
            e.printStackTrace();
        }
    }

    /**
     * Gestionnaire d'événement pour fermer uniquement la fenêtre modale de confirmation
     * Utilisé lorsque l'utilisateur annule son action de sortie
     *
     * @param event L'événement de clic sur le bouton d'annulation (peut être null si appelé programmatiquement)
     */
    @FXML
    void closeModal(ActionEvent event) {
        // Récupérer et fermer la fenêtre modale
        Stage modalStage = (Stage) leaveBtn.getScene().getWindow();
        modalStage.close();
    }
}