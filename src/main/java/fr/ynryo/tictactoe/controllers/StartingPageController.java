package fr.ynryo.tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Contrôleur pour la page d'accueil du jeu
 * Gère la navigation principale de l'application, permettant à l'utilisateur de démarrer une partie,
 * d'accéder aux paramètres, au classement ou de quitter l'application
 */
public class StartingPageController {

    /**
     * Bouton permettant de fermer l'application
     */
    @FXML
    private Button close_btn;

    /**
     * Bouton permettant d'ouvrir la page des paramètres
     */
    @FXML
    private Button settings_btn;

    /**
     * Bouton permettant d'ouvrir la page du classement
     */
    @FXML
    private Button ranking_btn;

    /**
     * Bouton permettant de démarrer une nouvelle partie
     */
    @FXML
    private Button startgame_button;

    /**
     * Méthode d'initialisation appelée automatiquement après le chargement du FXML
     * Charge les polices personnalisées nécessaires à l'affichage
     */
    @FXML
    public void initialize() {
        // Chargement de la police Minecraft pour l'interface utilisateur
        Font.loadFont(getClass().getResource("/fr/ynryo/tictactoe/fonts/MinecraftRegular.otf").toExternalForm(), 10);
        // Désactivation, car fonctionnalité non implémentée
//        ranking_btn.setDisable(true);
    }

    /**
     * Gestionnaire d'événement pour le bouton de démarrage d'une nouvelle partie
     * Charge la page de configuration d'une nouvelle partie
     *
     * @param event L'événement de clic sur le bouton
     */
    @FXML
    void start_game(ActionEvent event) {
        try {
            // Récupérer la fenêtre actuelle
            Stage stage = (Stage) startgame_button.getScene().getWindow();

            // Charger la vue de configuration de nouvelle partie
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/config_new_game.fxml"));
            
            // Obtenir les dimensions de l'écran pour un affichage optimal
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            
            // Créer une nouvelle scène avec la vue chargée
            Scene scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
            
            // Appliquer les styles CSS
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());

            // Configurer et afficher la fenêtre
            stage.setScene(scene);
            stage.setTitle("MinePion - Configuration de la partie");
            stage.setMaximized(true);
        } catch (Exception e) {
            // Gestion des erreurs potentielles lors du chargement de la page
            System.err.println("Erreur lors de l'ouverture de la fenêtre de configuration de la partie:");
            e.printStackTrace();
        }
    }

    /**
     * Gestionnaire d'événement pour le bouton de fermeture de l'application
     * Ferme complètement l'application
     *
     * @param event L'événement de clic sur le bouton
     */
    @FXML
    void close_app(ActionEvent event) {
        // Récupérer et fermer la fenêtre principale
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Gestionnaire d'événement pour le bouton des paramètres
     * Charge la page des paramètres de l'application
     *
     * @param event L'événement de clic sur le bouton
     */
    @FXML
    void open_settings(ActionEvent event) {
        try {
            // Charger la vue des paramètres
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/settings.fxml"));
            Parent root = fxmlLoader.load();
            
            // Récupérer la fenêtre actuelle
            Stage stage = (Stage) settings_btn.getScene().getWindow();
            
            // Créer et configurer la nouvelle scène
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // Gestion des erreurs potentielles lors du chargement de la page
            System.err.println("Erreur lors de l'ouverture des paramètres :");
            e.printStackTrace();
        }
    }

    /**
     * Gestionnaire d'événement pour ouvrir la page de classement
     * Charge la vue du classement des joueurs
     *
     * @param event L'événement de clic sur le bouton (non mentionné dans les attributs FXML mais présent dans le FXML)
     */
    @FXML
    void ranking_open(ActionEvent event) {
        try {
            // Charger la vue du classement
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ynryo/tictactoe/fxml/ranking.fxml"));
            
            // Récupérer la fenêtre actuelle
            Stage stage = (Stage) ranking_btn.getScene().getWindow();
            
            // Créer et configurer la nouvelle scène
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());
            
            // Configurer et afficher la fenêtre
            stage.setScene(scene);
            // Astuce pour s'assurer que l'affichage maximisé est correctement appliqué
            stage.setMaximized(false);
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            // Gestion des erreurs potentielles lors du chargement de la page
            System.err.println("Erreur lors de l'ouverture du classement :");
            e.printStackTrace();
        }
    }
}