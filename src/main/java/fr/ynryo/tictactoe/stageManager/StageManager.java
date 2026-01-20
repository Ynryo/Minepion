package fr.ynryo.tictactoe.stageManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {

    private String title;
    private Stage originStage;
    private String fxmlLoaderPath;
    private FXMLLoader fxmlLoader;
    private StageTypes type;
    private ActionEvent event;

    public StageManager(String title, String fxmlLoaderPath, Stage originStage) {
        this.title = title;
        this.fxmlLoaderPath = fxmlLoaderPath;
        this.originStage = originStage;
        type = StageTypes.SCENE;
    }

    public StageManager(String title, String fxmlLoaderPath, Stage originStage, StageTypes type) {
        this.title = title;
        this.fxmlLoaderPath = fxmlLoaderPath;
        this.originStage = originStage;
        this.type = type;
    }

    public StageManager(String title, String fxmlLoaderPath, Stage originStage, StageTypes type,  ActionEvent event) {
        this.title = title;
        this.fxmlLoaderPath = fxmlLoaderPath;
        this.originStage = originStage;
        this.type = type;
        this.event = event;
    }

    public void loadStage() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(fxmlLoaderPath));
            Scene scene = null;
            if (type == StageTypes.SCENE) {
                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
            } else if (type == StageTypes.MODAL) scene = new Scene(fxmlLoader.load());

            Font.loadFont(getClass().getResource("/fr/ynryo/tictactoe/fonts/MinecraftRegular.otf").toExternalForm(), 10);

            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/game.css").toExternalForm());

            originStage.setScene(scene);
            originStage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
            originStage.setTitle(title);

        } catch (Exception e) {
            System.err.println("Stage Switcher Error " + e.getMessage());
        }
    }

    public void showStage() {
        switch (type) {
            case SCENE:
                originStage.setMaximized(true);
                originStage.show();
                break;

            case MODAL:
                originStage.setResizable(false);
                originStage.initModality(Modality.APPLICATION_MODAL);
                Node source = (Node) event.getSource();
                originStage.initOwner(source.getScene().getWindow());
                originStage.showAndWait();
                break;
        }
    }

    public void switchStage() {
        loadStage();
        showStage();
    }

    public FXMLLoader getFXMLLoader() throws IOException {
        return fxmlLoader;
    }
}
