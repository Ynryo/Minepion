package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;

public class GameController {

    @FXML
    private Button checkbox1;

    @FXML
    private Button checkbox2;

    @FXML
    private Button checkbox3;

    @FXML
    private Button checkbox4;

    @FXML
    private Button checkbox5;

    @FXML
    private Button checkbox6;

    @FXML
    private Button checkbox7;

    @FXML
    private Button checkbox8;

    @FXML
    private Button checkbox9;

    @FXML
    private Button close_game;

    @FXML
    private Label player1_name;

    @FXML
    private Label player1_score;

    @FXML
    private Label player2_name;

    @FXML
    private Label player2_score;

    @FXML
    private Label player_turn;

    @FXML
    private Button reset_game_btn;

    @FXML
    private Button rules_btn;

    private Player player1;
    private Player player2;
    private Button[][] checkboxes;
    private Player currentPlayer;
    private int nbActions;
    public Object[] values;

    @FXML
    void initialize() {
        // Initialisation correcte du tableau après injection FXML
        checkboxes = new Button[][]{
                {checkbox1, checkbox2, checkbox3},
                {checkbox4, checkbox5, checkbox6},
                {checkbox7, checkbox8, checkbox9}
        };
        values = new Object[]{"X", "O"};
        nbActions = 0;
        for (Button[] row : checkboxes) {
            for (Button check : row) {
                check.setText("");
            }
        }
    }

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
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Rules");
            stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Node source = (Node) event.getSource();
            stage.initOwner(source.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("Error opening rules window:");
            e.printStackTrace();
        }

    }

    @FXML
    void resetGame(ActionEvent event) {
        initialize();
        setPlayers(player1, player2);
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        player1_name.setText(player1.getName());
        player1_score.setText(String.valueOf(player1.getScoreInFile()));

        this.player2 = player2;
        player2_name.setText(player2.getName());
        player2_score.setText(String.valueOf(player2.getScoreInFile()));

        player_turn.setText(player1.getName());
        // values = new Object[]{player1.getSymbol(), player2.getSymbol()};
        currentPlayer = player1;
    }

    public void setPlayers(Player player1, Player player2, Player starter) {
        setPlayers(player1, player2);
        currentPlayer = starter;
    }

    @FXML
    void checked(ActionEvent event) {
        Button check = (Button) event.getSource();
        if (check.getText() == "") {
            check.setText(currentPlayer.getSymbol().toString());
            nbActions++;
        } else {
            return;
        }
        if (checkWin() && !checkDraw()) {
            currentPlayer.increaseScore();
            try {
                // Appelé dans le GameController à l'ouverture de la fenêtre "victoire"
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/game_end_win.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                GameEndWinController controller = fxmlLoader.getController();
                controller.setGameController(this); // <-- On passe le contrôleur courant
                controller.setCurrentPlayer(currentPlayer);
                stage.setTitle("Victoire de " + currentPlayer.getName() + " !");
                stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(check.getScene().getWindow());
                stage.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (checkDraw() && !checkWin()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/game_end_draw.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Egalité");
                stage.getIcons().add(new Image("https://cdn3d.iconscout.com/3d/premium/thumb/arcade-game-3d-icon-download-in-png-blend-fbx-gltf-file-formats--video-machine-gaming-pack-sign-symbols-icons-7308667.png"));
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(check.getScene().getWindow());
                stage.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        player_turn.setText(currentPlayer.getName());
    }

    public boolean checkDraw() {
        return nbActions >= 9;
    }

    public boolean checkWin() {
        //check h
        for (Button[] row : checkboxes) {
            if (row[0].getText().equals(currentPlayer.getSymbol()) && row[1].getText().equals(currentPlayer.getSymbol()) && row[2].getText().equals(currentPlayer.getSymbol())) {
                System.out.println("win h");
                return true;
            }
        }
        //check v
        for (int col = 0; col < 3; col++) {
            if (checkboxes[0][col].getText().equals(currentPlayer.getSymbol()) &&
                checkboxes[1][col].getText().equals(currentPlayer.getSymbol()) &&
                checkboxes[2][col].getText().equals(currentPlayer.getSymbol())) {
                System.out.println("win v");
                return true;
            }
        }
        //check d
        if ((checkboxes[0][0].getText().equals(currentPlayer.getSymbol()) &&
             checkboxes[1][1].getText().equals(currentPlayer.getSymbol()) &&
             checkboxes[2][2].getText().equals(currentPlayer.getSymbol())) ||
            (checkboxes[0][2].getText().equals(currentPlayer.getSymbol()) &&
             checkboxes[1][1].getText().equals(currentPlayer.getSymbol()) &&
             checkboxes[2][0].getText().equals(currentPlayer.getSymbol()))) {
            System.out.println("win d");
            return true;
        }
        return false;
    }

    public int getNbActions() {
        return nbActions;
    }

    public void setNbActions(int nbActions) {
        this.nbActions = nbActions;
    }
}