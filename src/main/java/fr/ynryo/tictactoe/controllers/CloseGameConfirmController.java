package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CloseGameConfirmController {

    @FXML
    private Button confirmBtn;

    @FXML
    private Button leaveBtn;

    @FXML
    void closeGame(ActionEvent event) {
        try {
            Stage modalStage = (Stage) confirmBtn.getScene().getWindow();
            closeModal(null);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/starting_page.fxml"));
            Parent root = fxmlLoader.load();
            Stage mainStage = (Stage) modalStage.getOwner();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            System.err.println("Error while closing game and returning to start page:");
            e.printStackTrace();
        }
    }

    @FXML
    void closeModal(ActionEvent event) {
        Stage modalStage = (Stage) leaveBtn.getScene().getWindow();
        modalStage.close();
    }

}
