package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameController {

    @FXML
    private Button close_game;

    @FXML
    void closeGameClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/close_game_confirm.fxml"));
            Parent root = fxmlLoader.load();
        } catch (Exception e) {

        }


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/starting_page.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) close_game.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setMaximized(true);
            //TO-DO: faut ouvrir une modale de confirmation
            stage.show();
        } catch (Exception e) {
            System.err.println("c pt");
            e.printStackTrace();
        }
    }

}
