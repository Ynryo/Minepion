package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorsController {

    @FXML
    private Button close_btn;   // Bouton pour fermer la fenêtre d'erreur

    @FXML
    private Label error_desc;   // Label pour afficher la description détaillée de l'erreur

    @FXML
    private Label error_name;   // Label pour afficher le titre/nom de l'erreur

    public void setErrorText(String error_name, String error_desc) {
        this.error_desc.setText(error_desc);
        this.error_name.setText(error_name);
    }

    @FXML
    void close_modal(ActionEvent event) {
        ((Stage) close_btn.getScene().getWindow()).close();
    }

}