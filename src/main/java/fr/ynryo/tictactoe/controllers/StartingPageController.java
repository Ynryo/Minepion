package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartingPageController {

    @FXML
    private Button close_btn;

    @FXML
    private Button settings_btn;

    @FXML
    private Button startgame_button;

    @FXML
    void start_game(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) startgame_button.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            System.err.println("c pt");
            e.printStackTrace();
        }
    }

    @FXML
    void close_app(ActionEvent event) {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void open_settings(ActionEvent event) {

    }
}
