package fr.ynryo.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.net.URL;

/**
 * Classe principale de l'application MinePion
 * Point d'entrée principal de l'application qui initialise l'interface utilisateur JavaFX
 * Hérite de Application pour intégrer le cycle de vie standard d'une application JavaFX
 */
public class Main extends Application {
    
    /**
     * Méthode de démarrage de l'application JavaFX
     * Initialise et configure l'interface utilisateur principale
     * 
     * @param stage La fenêtre principale de l'application
     * @throws IOException En cas d'erreur lors du chargement des ressources
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Chargement de la police personnalisée Minecraft pour l'interface
        Font.loadFont(getClass().getResource("/fr/ynryo/tictactoe/fonts/MinecraftRegular.otf").toExternalForm(), 10);

        // Chargement du fichier FXML de la page d'accueil
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fr/ynryo/tictactoe/fxml/starting_page.fxml"));
        
        // Récupération des dimensions de l'écran pour un affichage optimal
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        
        // Création de la scène principale avec les dimensions de l'écran
        Scene scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        
        // Application de la feuille de style CSS
        scene.getStylesheets().add(getClass().getResource("/fr/ynryo/tictactoe/css/style.css").toExternalForm());

        // Configuration de l'icône de l'application
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/fr/ynryo/tictactoe/images/favicon/favicon.png")));
        
        // Configuration du titre de la fenêtre
        stage.setTitle("MinePion");
        
        // Configuration de la fenêtre en mode maximisé
        stage.setMaximized(true);

        // Association de la scène à la fenêtre principale
        stage.setScene(scene);
        
        // Affichage de la fenêtre
        stage.show();
    }

    /**
     * Point d'entrée principal de l'application
     * Démarre le cycle de vie de l'application JavaFX
     * 
     * @param args Arguments de ligne de commande (non utilisés dans cette application)
     */
    public static void main(String[] args) {
        launch(); // Lance le cycle de vie JavaFX
    }
}