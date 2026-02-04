# **Minepion ⛏️❌⭕**

**Minepion** est une réinterprétation moderne et ludique du célèbre jeu de Morpion (Tic-Tac-Toe), entièrement immergée dans l'univers visuel et sonore de **Minecraft**. Développé en Java avec l'interface **JavaFX**, ce projet allie nostalgie et stratégie.

## **✨ Fonctionnalités**

* 🎮 **Modes de Jeu Variés** :  
  * **Joueur contre Joueur (PvP)** : Affrontez un ami en local.  
  * **Joueur contre IA (PvE)** : Challengez l'ordinateur avec une intelligence artificielle intégrée.  
* 🎨 **Immersion Minecraft Totale** :  
  * Interface utilisateur stylisée "Menu Minecraft" (boutons, fonds, polices).  
  * Effets sonores authentiques (clics, musiques d'ambiance).  
  * Utilisation d'items du jeu comme pions.  
* 🏆 **Système de Classement** :  
  * Suivi des scores et des victoires.  
  * Historique des joueurs sauvegardé localement (JSON).  
* ⚙️ **Personnalisation** :  
  * Configuration des parties (choix des pseudos, symboles).  
  * Gestion des profils joueurs.

## **🛠️ Stack Technique**

* **Langage** : Java 17+ (Logique objet robuste).  
* **Interface Graphique** : JavaFX (FXML pour la structure, CSS pour le style).  
* **Gestion de Build** : Maven (Gestion des dépendances et du cycle de vie).  
* **Persistance des Données** : JSON (Bibliothèque `org.json` ou GSON probable pour la sauvegarde des joueurs).  
* **Ressources** : Textures et sons inspirés de Minecraft (Mojang Studios).

## **🚀 Installation & Lancement**

### **Prérequis**

* **JDK 17** ou supérieur installé.  
* **Maven** (ou l'utiliser via le wrapper `mvnw` inclus).  
* Un IDE compatible JavaFX (IntelliJ IDEA recommandé, ou Eclipse/VS Code).

### **Démarrage Rapide**

1. **Cloner le projet** :  
   ```
   git clone https://github.com/Ynryo/minepion.git   
   cd minepion
   ```

2. **Compiler le projet** (téléchargement des dépendances) :  
   * *Via terminal (Linux/Mac)* :  
     `./mvnw clean install`

   * *Via terminal (Windows)* :  
     `mvnw.cmd clean install`

3. **Lancer le jeu** :  
   Vous pouvez lancer l'application directement via Maven :  
   `./mvnw javafx:run`

   Ou exécuter la classe principale dans votre IDE : `src/main/java/fr/ynryo/tictactoe/Launcher.java`

## **📂 Structure du Projet**

L'architecture suit le modèle standard Maven et le pattern MVC :

* `src/main/java/fr/ynryo/tictactoe/` : Code source Java.  
  * `controllers/` : Gestion des événements (clics boutons, logique de jeu).  
  * `stageManager/` : Gestion des différentes fenêtres (Menu, Jeu, Options).  
  * `IA.java` : Logique de l'intelligence artificielle.  
  * `JsonManipulator.java` : Lecture/Écriture des données joueurs.  
* src/main/resources/fr/ynryo/tictactoe/` : Ressources graphiques et configs.  
  * `fxml/` : Vues de l'application (Fichiers .fxml).  
  * `css/` : Styles pour donner l'aspect Minecraft (game.css, style.css).  
  * `images/` : Textures, items et fonds d'écran.  
  * `sounds/` : Effets sonores et musiques.  
  * `fonts/` : Polices d'écriture Minecraftia.

## **📝 À propos**

Ce projet est une création étudiante réalisée par **Ynryo**, n'est pas affilié à Mojang ou Microsoft. Les assets graphiques et sonores restent la propriété de leurs créateurs respectifs.