package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.stageManager.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartingPageController {

    @FXML
    private Button close_btn;

    @FXML
    private Button settings_btn;

    @FXML
    private Button ranking_btn;

    @FXML
    private Button startgame_button;

    @FXML
    void start_game(ActionEvent event) {
        StageManager stageManager = new StageManager(
                "MinePion - Configuration de la partie",
                "/fr/ynryo/tictactoe/fxml/config_new_game.fxml",
                (Stage) startgame_button.getScene().getWindow()
        );
        stageManager.switchStage();
    }

    @FXML
    void close_app(ActionEvent event) {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void open_settings(ActionEvent event) {
        StageManager stageManager = new StageManager(
                "MinePion - Paramètres",
                "/fr/ynryo/tictactoe/fxml/settings.fxml",
                (Stage) settings_btn.getScene().getWindow()
        );
        stageManager.switchStage();
    }

    @FXML
    void ranking_open(ActionEvent event) {
        StageManager stageManager = new StageManager(
                "MinePion - Classement",
                "/fr/ynryo/tictactoe/fxml/ranking.fxml",
                (Stage) ranking_btn.getScene().getWindow()
        );
        stageManager.switchStage();
    }
}