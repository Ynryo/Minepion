package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.IA;
import fr.ynryo.tictactoe.JsonManipulator;
import fr.ynryo.tictactoe.Player;
import fr.ynryo.tictactoe.stageManager.StageManager;
import fr.ynryo.tictactoe.stageManager.StageTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Contrôleur gérant la configuration d'une nouvelle partie
 * Permet de configurer les noms des joueurs, leurs symboles et les options de démarrage
 */
public class ConfigNewGameController {

    @FXML
    private TextField input_player1_name;  // Nom du joueur 1

    @FXML
    private TextField input_player1_symbol;  // Symbole du joueur 1

    @FXML
    private TextField input_player2_name;  // Nom du joueur 2

    @FXML
    private TextField input_player2_symbol;  // Symbole du joueur 2

    @FXML
    private Button one_player_mode;  // Mode 1 joueur (contre IA)

    @FXML
    private Button two_players_mode;  // Mode 2 joueurs

    @FXML
    private RadioButton radioStartP1;  // Le joueur 1 commence

    @FXML
    private RadioButton radioStartP2;  // Le joueur 2 commence

    @FXML
    private RadioButton radioStartRDM;  // Choix aléatoire du joueur qui commence

    @FXML
    private Button start_game_btn;  // Bouton pour démarrer le jeu

    @FXML
    private ImageView item_img1;  // Aperçu du symbole du joueur 1

    @FXML
    private ImageView item_img2;  // Aperçu du symbole du joueur 2

    @FXML
    private Button symbolBtn1;  // Bouton pour changer le symbole du joueur 1

    @FXML
    private Button symbolBtn2;  // Bouton pour changer le symbole du joueur 2

    private int nbPlayerMode = 2;  // Mode de jeu (1 ou 2 joueurs)
    private Player player1;  // Objet représentant le joueur 1
    private Player player2;  // Objet représentant le joueur 2
    private final List<String> imageItems = new ArrayList<>();  // Liste des fichiers d'images disponibles
    private int currentImageIndex1 = 0;  // Index actuel dans la liste pour le symbole du joueur 1
    private int currentImageIndex2 = 0;  // Index actuel dans la liste pour le symbole du joueur 2

    JsonManipulator jsonManipulator = new JsonManipulator("src/main/resources/fr/ynryo/tictactoe/json/player_data.json");

    @FXML
    void initialize() {
//        one_player_mode.setDisable(true);
        nbPlayerMode = 2;

        loadImageItems();
        
        item_img1.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/barrier.png"), 80, 80, false, false));
        item_img2.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/bucket.png"), 80, 80, false, false));
    }

    private void loadImageItems() {
        File directory = new File("src/main/resources/fr/ynryo/tictactoe/images/item");
        if (directory.exists() && directory.isDirectory()) {
            String[] files = directory.list();
            if (files != null) {
                imageItems.addAll(Arrays.asList(files));
            }
        }
    }

    public int getNbPlayerMode() {
        return nbPlayerMode;
    }

    public void setNbPlayerMode(int nbPlayerMode) {
        this.nbPlayerMode = nbPlayerMode;
    }

    @FXML
    void onePlayerSelected(ActionEvent event) {
        setNbPlayerMode(1);
        input_player2_name.setText("Ordinateur");
        input_player2_symbol.setText("redstone");
        item_img2.setDisable(true);
        /*item_img2.setImage();*/
        input_player2_name.setDisable(true);
        input_player2_symbol.setDisable(true);
    }

    @FXML
    void twoPlayersSelected(ActionEvent event) {
        setNbPlayerMode(2);
        input_player2_name.setText("");
        input_player2_symbol.setText("redstone");
        item_img2.setDisable(false);
        input_player2_name.setDisable(false);
        input_player2_symbol.setDisable(false);
    }

    @FXML
    public void startGame(ActionEvent event) throws IOException {
        player1 = new Player(input_player1_name.getText(), input_player1_symbol.getText());
        player2 = new Player(input_player2_name.getText(), input_player2_symbol.getText());

        if ((player1 == null || player2 == null) || (player1.getName().equals(player2.getName())) || (player1.getSymbol().equals(player2.getSymbol()))) {

            StageManager stageManager = new StageManager(
                    "Erreur",
                    "/fr/ynryo/tictactoe/fxml/modals/error.fxml",
                    new Stage(),
                    StageTypes.MODAL,
                    event
            );
            stageManager.loadStage();

            String error_name = "";
            String error_desc = "";
            if (player1.getName().isEmpty() || player2.getName().isEmpty() || player1.getSymbol().isEmpty() || player2.getSymbol().isEmpty()) {
                    error_name = "Des champs sont vides";
                    error_desc = "Un ou plusieurs champs sont vides";
            } else if (player1.getName().equals(player2.getName())) {
                error_name = "Pseudos égaux";
                error_desc = "Les pseudos sont égaux";
            } else if (player1.getSymbol().equals(player2.getSymbol())) {
                error_name = "Symboles égaux";
                error_desc = "Les symboles sont égaux";
            }

            ErrorsController errorsController = stageManager.getFXMLLoader().getController();
            errorsController.setErrorText(error_name, error_desc);

            stageManager.showStage();
            return;
        }

        switch (getNbPlayerMode()) {
            case 1:
                IA ia = new IA(1);
                try {
                    Stage modalStage = (Stage) two_players_mode.getScene().getWindow();
                    Stage mainStage = (Stage) modalStage.getOwner();
                    modalStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/game.fxml"));
                    Parent root = fxmlLoader.load();

                    GameController gameController = fxmlLoader.getController();
                    gameController.setPlayers(player1, ia, whoStart());

                    mainStage.setScene(new Scene(root));
                    mainStage.show();
                } catch (Exception e) {
                    System.err.println("Erreur lors du lancement de la partie en mode 1 joueurs :");
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    StageManager stageManager = new StageManager(
                            "MinePion - Partie en mode 2 joueurs",
                            "/fr/ynryo/tictactoe/fxml/game.fxml",
                            (Stage) two_players_mode.getScene().getWindow()
                    );
                    stageManager.switchStage();
                    GameController gameController = stageManager.getFXMLLoader().getController();
                    gameController.setPlayers(player1, player2, whoStart());
                } catch (Exception e) {
                    System.err.println("Erreur lors du lancement de la partie en mode 2 joueurs :");
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Sélectionnez un mode entre 1 ou 2 joueurs.");
        }
    }

    /**
     * Détermine quel joueur doit commencer la partie selon la sélection
     * @return Le joueur qui doit commencer
     */
    private Player whoStart() {
        if (radioStartP1.isSelected()) {
            return player1;
        } else if (radioStartP2.isSelected()) {
            return player2;
        } else if (radioStartRDM.isSelected()) {
            return new Random().nextBoolean() ? player1 : player2;
        }
        return null;
    }

    /**
     * Met à jour le symbole du joueur à partir de son nom
     * Cherche dans le fichier JSON si le joueur a un symbole enregistré
     */
    @FXML
    void updateSymbol(KeyEvent event) {
        TextField sourceField = (TextField) event.getSource();
        TextField symbolField = sourceField == input_player1_name ? input_player1_symbol : input_player2_symbol;
        ImageView sourceImg = sourceField == input_player1_name ? item_img1 : item_img2;
        String defaultSymbol = sourceField == input_player1_name ? "barrier" : "bucket";
        String playerName = sourceField.getText();

        // Chercher le symbole dans le fichier JSON
        String symbolFromJson = (String) jsonManipulator.read(playerName, "symbol");

        if (!symbolFromJson.isEmpty()) {
            // Si un symbole existe pour ce joueur, l'utiliser
            if (imageItems.contains(symbolFromJson + ".png")) {
                symbolField.setText(symbolFromJson);
                sourceImg.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + symbolFromJson + ".png"), 80, 80, false, false));
                if (sourceField == input_player1_symbol) {
                    currentImageIndex1 = imageItems.indexOf(sourceField.getText() + ".png");
                } else {
                    currentImageIndex2 = imageItems.indexOf(sourceField.getText() + ".png");
                }
            }
        } else {
            // Sinon, utiliser le symbole par défaut
            symbolField.setText(defaultSymbol);
            sourceImg.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + defaultSymbol + ".png"), 80, 80, false, false));
        }
    }

    /**
     * Met à jour l'aperçu de l'image lorsque le symbole est modifié
     */
    @FXML
    void updateImg(KeyEvent event) {
        TextField sourceField = (TextField) event.getSource();
        ImageView sourceImg = sourceField == input_player1_symbol ? item_img1 : item_img2;

        // Vérifier si le symbole saisi correspond à une image existante
        if (!sourceField.getText().isEmpty() && imageItems.contains(sourceField.getText() + ".png")) {
            sourceImg.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + sourceField.getText() + ".png"), 80, 80, false, false));
            if (sourceField == input_player1_symbol) {
                currentImageIndex1 = imageItems.indexOf(sourceField.getText() + ".png");
            } else {
                currentImageIndex2 = imageItems.indexOf(sourceField.getText() + ".png");
            }
        }
    }

    /**
     * Change le symbole du joueur au clic sur le bouton correspondant
     * Parcourt la liste des images disponibles de manière cyclique
     */
    @FXML
    void changeItemImg(ActionEvent event) {
        Button sourceBtn = (Button) event.getSource();
        ImageView sourceImg = sourceBtn == symbolBtn1 ? item_img1 : item_img2;

        if (sourceBtn == symbolBtn1) {
            // Changer le symbole du joueur 1
            currentImageIndex1 = getNextImageIndex(currentImageIndex1);
            sourceImg.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + imageItems.get(currentImageIndex1)), 80, 80, false, false));
            input_player1_symbol.setText(imageItems.get(currentImageIndex1).split("\\.")[0]);
        } else {
            // Changer le symbole du joueur 2
            currentImageIndex2 = getNextImageIndex(currentImageIndex2);
            sourceImg.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + imageItems.get(currentImageIndex2)), 80, 80, false, false));
            input_player2_symbol.setText(imageItems.get(currentImageIndex2).split("\\.")[0]);
        }
    }

    /**
     * Calcule l'index de la prochaine image dans la liste
     * Gère la rotation cyclique (revient au début après la dernière image)
     */
    private int getNextImageIndex(int currentIndex) {
        return (currentIndex + 1) % imageItems.size();
    }
}