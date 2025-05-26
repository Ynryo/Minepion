package fr.ynryo.tictactoe.controllers;

import fr.ynryo.tictactoe.*;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur principal du jeu Tic Tac Toe
 * Gère l'interface et la logique du jeu
 */
public class GameController {

    // Éléments d'interface utilisateur injectés depuis le fichier FXML
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
    private ImageView item_img1;

    @FXML
    private ImageView item_img2;

    @FXML
    private ImageView item_img3;

    @FXML
    private ImageView item_img4;

    @FXML
    private ImageView item_img5;

    @FXML
    private ImageView item_img6;

    @FXML
    private ImageView item_img7;

    @FXML
    private ImageView item_img8;

    @FXML
    private ImageView item_img9;

    @FXML
    private Label player1_name;

    @FXML
    private Label player1_score;

    @FXML
    private ImageView player1_symbol;

    @FXML
    private Label player2_name;

    @FXML
    private Label player2_score;

    @FXML
    private ImageView player2_symbol;

    @FXML
    private Label player_turn;

    @FXML
    private Button reset_game_btn;

    @FXML
    private Button rules_btn;

    // Variables pour gérer l'état du jeu
    private Player player1;                // Premier joueur
    private Player player2;                // Second joueur
    private Button[][] checkboxes;         // Tableau 2D des boutons de la grille
    private ImageView[][] itemImages;      // Tableau 2D des images de symboles
    private String[][] boardState;         // État actuel du plateau (contient les symboles)
    private Player currentPlayer;          // Joueur dont c'est le tour
    private Player starter;                // Joueur qui commence la partie actuelle
    private Player originalStarter;        // Joueur qui a commencé la première partie (pour alterner)
    private int nbActions;                 // Nombre de coups joués dans la partie en cours
    private final List winningCheckboxes = new ArrayList<CheckBox>(); // Liste des cases de la combinaison gagnante

    /**
     * Initialise la grille de jeu et réinitialise l'état
     */
    @FXML
    void initialize() {
        // Organisation des contrôles en tableaux 2D pour faciliter leur manipulation
        checkboxes = new Button[][]{
                {checkbox1, checkbox2, checkbox3},
                {checkbox4, checkbox5, checkbox6},
                {checkbox7, checkbox8, checkbox9}
        };
        itemImages = new ImageView[][]{
                {item_img1, item_img2, item_img3},
                {item_img4, item_img5, item_img6},
                {item_img7, item_img8, item_img9}
        };
        
        // Initialisation du tableau d'état et du compteur d'actions
        boardState = new String[3][3];
        nbActions = 0;
        
        // Effacement des symboles précédents dans l'interface
        for (ImageView[] row : itemImages) {
            for (ImageView img : row) {
                img.setImage(null);
            }
        }
        
        // Réinitialisation du tableau d'état
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardState[i][j] = null;
            }
        }
        
        // Effacement de la liste des cases gagnantes
        winningCheckboxes.clear();
    }

    /**
     * Affiche une fenêtre de confirmation pour fermer le jeu
     */
    @FXML
    void closeGameClicked(ActionEvent event) {
        try {
            // Chargement de la fenêtre modale de confirmation
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/close_game_confirm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Application du style CSS
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());

            // Configuration de la fenêtre
            stage.setTitle("Fermer la partie ?");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);  // Bloque l'interaction avec la fenêtre parent
            stage.setScene(scene);

            // Définition de la fenêtre parente
            Node source = (Node) event.getSource();
            stage.initOwner(source.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ouverture de la fenêtre de confirmation");
            e.printStackTrace();
        }
    }

    /**
     * Affiche les règles du jeu dans une fenêtre modale
     */
    @FXML
    void openRules(ActionEvent event) {
        try {
            // Chargement de la fenêtre modale des règles
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/rules.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());

            // Configuration de la fenêtre
            stage.setTitle("Règles du jeu");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            // Définition de la fenêtre parente
            Node source = (Node) event.getSource();
            stage.initOwner(source.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            System.err.println("Erreur durant l'ouverture de la fenètre des règles du jeu");
            e.printStackTrace();
        }
    }

    /**
     * Réinitialise la partie en cours sans changer les scores
     */
    @FXML
    void resetGame(ActionEvent event) {
        initialize();  // Réinitialise le plateau
        setPlayers(player1, player2, starter);  // Conserve les mêmes joueurs
    }

    /**
     * Configure les joueurs et leurs informations dans l'interface
     */
    public void setPlayers(Player player1, Player player2) {
        // Configuration du joueur 1
        this.player1 = player1;
        player1_name.setText(player1.getName());
        player1_score.setText(String.valueOf(player1.getScoreInFile()));
        player1_symbol.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + player1.getSymbolInFile() + ".png"), 150, 150, false, false));

        // Configuration du joueur 2
        this.player2 = player2;
        player2_name.setText(player2.getName());
        player2_score.setText(String.valueOf(player2.getScoreInFile()));
        player2_symbol.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + player2.getSymbolInFile() + ".png"), 150, 150, false, false));
    }

    /**
     * Configure les joueurs et définit qui commence la partie
     */
    public void setPlayers(Player player1, Player player2, Player starter) {
        this.starter = starter;
        // Mémorise le joueur qui a commencé la première partie pour alterner par la suite
        if (this.originalStarter == null) {
            this.originalStarter = starter;
        }
        // Définit le joueur actuel et met à jour l'interface
        currentPlayer = starter;
        player_turn.setText(starter.getName());
        // Configure les informations des joueurs
        setPlayers(player1, player2);
    }

    /**
     * Gère le clic sur une case du jeu
     */
    @FXML
    void checked(ActionEvent event) {
        Button check = (Button) event.getSource();
        
        // Vérifie si la case est vide
        if (getImageViewForCheckbox(check).getImage() == null) {
            // Récupère l'ImageView associé au bouton et place le symbole du joueur actuel
            ImageView targetImageView = getImageViewForCheckbox(check);
            targetImageView.setImage(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/item/" + currentPlayer.getSymbolInFile() + ".png"), 65, 65, false, false));
            
            // Enregistre le symbole dans le tableau d'état
            for (int i = 0; i < checkboxes.length; i++) {
                for (int j = 0; j < checkboxes[i].length; j++) {
                    if (checkboxes[i][j] == check) {
                        boardState[i][j] = currentPlayer.getSymbolInFile();
                        break;
                    }
                }
            }
            
            // Incrémente le nombre d'actions (important pour détecter les matchs nuls)
            nbActions++;

            // Vérifie si le coup actuel est gagnant
            if (checkWin()) {
                // Incrémente le score du joueur
                currentPlayer.increaseScore();
                try {
                    // Charge et affiche la fenêtre de victoire
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/game_end_win.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(fxmlLoader.load()));

                    // Configure le contrôleur de la fenêtre de victoire
                    GameEndWinController controller = fxmlLoader.getController();
                    controller.setCurrentPlayer(currentPlayer, player1 == currentPlayer ? player2 : player1);
                    controller.setGameController(this); // Passe une référence au contrôleur du jeu

                    stage.setTitle("Victoire de " + currentPlayer.getName() + " !");

                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(check.getScene().getWindow());
                    stage.showAndWait();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            // Vérifie s'il y a match nul (grille pleine sans gagnant)
            else if (checkDraw()) {
                try {
                    // Charge et affiche la fenêtre de match nul
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/modals/game_end_draw.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(fxmlLoader.load()));
                    
                    // Configure le contrôleur de la fenêtre de match nul
                    GameEndDrawController controller = fxmlLoader.getController();
                    controller.setPlayers(player1, player2);
                    controller.setGameController(this);
                    
                    stage.setTitle("Egalité");

                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(check.getScene().getWindow());
                    stage.showAndWait();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            // Si le jeu continue, passe au joueur suivant
            else {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                player_turn.setText(currentPlayer.getName());
            }
        }
    }

    /**
     * Vérifie s'il y a match nul (grille pleine)
     */
    public boolean checkDraw() {
        return nbActions >= 9;
    }

    /**
     * Vérifie si le joueur actuel a gagné
     */
    public boolean checkWin() {
        System.out.println("CheckWin pour " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ")");
        
        String symbol = currentPlayer.getSymbolInFile();
        
        // Vérification des lignes
        for (int i = 0; i < 3; i++) {
            if (boardState[i][0] != null && boardState[i][0].equals(symbol) &&
                boardState[i][1] != null && boardState[i][1].equals(symbol) &&
                boardState[i][2] != null && boardState[i][2].equals(symbol)) {
                System.out.println("win h");
                // ATTENTION: Stockage des symboles au lieu des cases (bug)
                winningCheckboxes.add(boardState[i][0]);
                winningCheckboxes.add(boardState[i][1]);
                winningCheckboxes.add(boardState[i][2]);
                // playAnimation();
                return true; // Manquant dans le code original
            }
        }
        
        // Vérification des colonnes
        for (int j = 0; j < 3; j++) {
            if (boardState[0][j] != null && boardState[0][j].equals(symbol) &&
                boardState[1][j] != null && boardState[1][j].equals(symbol) &&
                boardState[2][j] != null && boardState[2][j].equals(symbol)) {
                System.out.println("win v");
                // ATTENTION: Stockage des symboles au lieu des cases (bug)
                winningCheckboxes.add(boardState[0][j]);
                winningCheckboxes.add(boardState[1][j]);
                winningCheckboxes.add(boardState[2][j]);
                // playAnimation();
                return true;
            }
        }
        
        // Vérification de la diagonale principale (haut-gauche à bas-droite)
        if (boardState[0][0] != null && boardState[0][0].equals(symbol) &&
            boardState[1][1] != null && boardState[1][1].equals(symbol) &&
            boardState[2][2] != null && boardState[2][2].equals(symbol)) {
            System.out.println("win d1");
            // ATTENTION: Stockage des symboles au lieu des cases (bug)
            winningCheckboxes.add(boardState[0][0]);
            winningCheckboxes.add(boardState[1][1]);
            winningCheckboxes.add(boardState[2][2]);
            // playAnimation();
            return true;
        }

        // Vérification de la diagonale secondaire (haut-droite à bas-gauche)
        if (boardState[0][2] != null && boardState[0][2].equals(symbol) &&
            boardState[1][1] != null && boardState[1][1].equals(symbol) &&
            boardState[2][0] != null && boardState[2][0].equals(symbol)) {
            System.out.println("win d2");
            // ATTENTION: Stockage des symboles au lieu des cases (bug)
            winningCheckboxes.add(boardState[0][2]);
            winningCheckboxes.add(boardState[1][1]);
            winningCheckboxes.add(boardState[2][0]);
            // playAnimation();
            return true;
        }
        return false;
    }

    /**
     * Anime les cases de la combinaison gagnante (fonction non utilisée)
     * NOTE: Cette fonction contient des bugs et n'est pas appelée actuellement
     */
    private void playAnimation() {
        if (!winningCheckboxes.isEmpty()) {
            for (Object checkBox : winningCheckboxes) {
                System.out.println("animation playing");
                // ATTENTION: Cast incorrect - checkBox contient des String, pas des Node
                ScaleTransition transition = new ScaleTransition(Duration.seconds(1), (Node) checkBox);
                transition.setToX(0.2);
                transition.setToY(0.2);
                transition.setInterpolator(Interpolator.EASE_BOTH);
                transition.play();
            }
        }
        // Pause de 2 secondes après l'animation
        PauseTransition pt = new PauseTransition(Duration.seconds(2));
        pt.play();

        winningCheckboxes.clear();
    }

    /**
     * Retourne le nombre d'actions jouées
     */
    public int getNbActions() {
        return nbActions;
    }

    /**
     * Définit le nombre d'actions jouées
     */
    public void setNbActions(int nbActions) {
        this.nbActions = nbActions;
    }

    /**
     * Trouve l'ImageView associé à un bouton de la grille
     */
    public ImageView getImageViewForCheckbox(Button checkbox) {
        for (int i = 0; i < checkboxes.length; i++) {
            for (int j = 0; j < checkboxes[i].length; j++) {
                if (checkboxes[i][j] == checkbox) {
                    return itemImages[i][j];
                }
            }
        }
        return null;
    }
    
    /**
     * Retourne le joueur qui a commencé la première partie
     * Utilisé pour alterner qui commence
     */
    public Player getOriginalStarter() {
        return originalStarter;
    }
}