package fr.ynryo.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fr/ynryo/tictactoe/fxml/starting_page.fxml"));
        Parent root = fxmlLoader.load();

        stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
        stage.setTitle("Tic Tac Toe");
        stage.setMaximized(true);
        Scene scene = new Scene(root, 480, 320);
        scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/fontstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}