package fr.ynryo.tictactoe;

import fr.ynryo.tictactoe.stageManager.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StageManager stageManager = new StageManager(
                "MinePion",
                "/fr/ynryo/tictactoe/fxml/starting_page.fxml",
                stage
        );
        stageManager.switchStage();
    }

    public static void main(String[] args) {
        launch();
    }
}