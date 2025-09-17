package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.stageManager.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CloseGameConfirmController {

    @FXML
    private Button confirmBtn;  // Bouton pour confirmer la sortie de la partie

    @FXML
    private Button leaveBtn;    // Bouton pour annuler et rester dans la partie en cours

    @FXML
    void closeGame(ActionEvent event) {
        Stage modalStage = (Stage) confirmBtn.getScene().getWindow();
        closeModal(null);
        StageManager stageManager = new StageManager(
                "MinePion",
                "/fr/ynryo/tictactoe/fxml/starting_page.fxml",
                (Stage) modalStage.getOwner()
        );
        stageManager.switchStage();
    }

    @FXML
    void closeModal(ActionEvent event) {
        Stage modalStage = (Stage) leaveBtn.getScene().getWindow();
        modalStage.close();
    }
}