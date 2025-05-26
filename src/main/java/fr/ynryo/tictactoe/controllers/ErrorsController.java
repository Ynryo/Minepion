package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Contrôleur de la fenêtre d'affichage des erreurs
 * Gère l'affichage des messages d'erreur dans une fenêtre modale
 */
public class ErrorsController {

    // Composants d'interface utilisateur injectés depuis le fichier FXML
    @FXML
    private Button close_btn;   // Bouton pour fermer la fenêtre d'erreur

    @FXML
    private Label error_desc;   // Label pour afficher la description détaillée de l'erreur

    @FXML
    private Label error_name;   // Label pour afficher le titre/nom de l'erreur

    /**
     * Configure le texte d'erreur à afficher dans la fenêtre modale
     * Cette méthode est appelée par d'autres contrôleurs pour définir le message d'erreur
     * 
     * @param error_name Le titre ou le type de l'erreur
     * @param error_desc La description détaillée de l'erreur
     */
    public void setErrorText(String error_name, String error_desc) {
        this.error_desc.setText(error_desc);
        this.error_name.setText(error_name);
    }

    /**
     * Gère l'événement de clic sur le bouton de fermeture
     * Ferme la fenêtre modale d'erreur
     * 
     * @param event L'événement de clic sur le bouton
     */
    @FXML
    void close_modal(ActionEvent event) {
        // Récupère la fenêtre (Stage) à partir du bouton et la ferme
        ((Stage) close_btn.getScene().getWindow()).close();
    }

}