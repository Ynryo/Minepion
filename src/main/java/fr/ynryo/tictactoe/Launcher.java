package fr.ynryo.tictactoe;

/**
 * Classe abstraite servant de point d'entrée alternatif pour l'application
 * Permet de rediriger vers la classe principale Main pour le démarrage de l'application
 */
public abstract class Launcher {
    
    /**
     * Méthode principale qui sert de point d'entrée secondaire
     * Délègue le lancement de l'application à la classe Main
     * 
     * @param args Arguments de ligne de commande fournis lors du lancement
     */
    public static void main(String[] args) {
        Main.main(args);  // Délègue l'exécution à la méthode main de la classe Main
    }
}