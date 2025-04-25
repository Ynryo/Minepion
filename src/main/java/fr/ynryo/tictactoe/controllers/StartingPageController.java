package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/config_new_game.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Rules");
            stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Node source = (Node) event.getSource();
            stage.initOwner(source.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ouverture de la fenêtre de confirmation:");
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/settings.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) settings_btn.getScene().getWindow();
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
}
