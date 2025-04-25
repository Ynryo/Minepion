package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RulesController {

    @FXML
    private Button close_btn;

    @FXML
    void closeModal(ActionEvent event) {
        Stage modalStage = (Stage) close_btn.getScene().getWindow();
        modalStage.close();
    }

}
