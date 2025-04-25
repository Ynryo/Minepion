package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.Main;
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
import java.net.URL;

public class GameController {

    @FXML
    private Button close_game;

    @FXML
    private Button reset_game_btn;

    @FXML
    private Button rulesBtn;

    @FXML
    void closeGameClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/close_game_confirm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Confirm closing game");
            stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Node source = (Node) event.getSource();
            stage.initOwner(source.getScene().getWindow());
            stage.showAndWait();
            // Ajouter ici la gestion de la confirmation
            // (ex. : CloseGameConfirmController controller = fxmlLoader.getController();)
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ouverture de la fenêtre de confirmation:");
            e.printStackTrace();
        }
}

    @FXML
    void openRules(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/rules.fxml"));
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
    void resetGame(ActionEvent event) {

    }

}