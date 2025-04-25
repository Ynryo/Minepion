package fr.ynryo.tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class Stage {
    public void openStage(String title, URL ressource, boolean modal, boolean maximized) throws IOException {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(ressource);
        Parent root = fxmlLoader.load();

        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
        stage.setTitle(title);
        stage.setMaximized(maximized);

        Scene scene = new Scene(root, 480, 320);
//            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/fontstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        System.err.println("Erreur lors de l'affichage de la scene :");
        e.printStackTrace();
    }
}
}
