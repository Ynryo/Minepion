package fr.ynryo.tictactoe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant la manipulation de données au format JSON
 * Gère la lecture et l'écriture de données persistantes pour l'application
 * Utilisée principalement pour stocker les informations des joueurs (scores, symboles, etc.)
 */
public class JsonManipulator {
    /** Chemin vers le fichier JSON à manipuler */
    private final String path;
    
    /**
     * Constructeur initialisant le chemin du fichier JSON
     * 
     * @param path Chemin d'accès au fichier JSON de stockage
     */
    public JsonManipulator(String path) {
        this.path = path;
    }

    /**
     * Lit une valeur spécifique dans le fichier JSON
     * 
     * @param name Identifiant de l'entité (généralement le nom du joueur)
     * @param key Clé spécifique à lire (score, symbole, etc.)
     * @return L'objet correspondant à la clé, ou une valeur par défaut si non trouvé
     */
    public Object read(String name, String key) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {
            // Définition du type pour la désérialisation : une map de maps
            Type type = new TypeToken<Map<String, Map<String, Object>>>() {
            }.getType();
            // Désérialisation du fichier JSON
            Map<String, Map<String, Object>> data = gson.fromJson(reader, type);

            // Si les données sont nulles ou ne contiennent pas l'entité recherchée
            if (data == null || !data.containsKey(name)) {
                return getDefaultValue(key);
            }

            // Récupération de la valeur spécifique
            Object value = data.get(name).get(key);
            if (value == null) {
                return getDefaultValue(key);
            }

            // Analyse et conversion de la valeur selon le type attendu
            return parseValue(key, value);

        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur, retourne une valeur par défaut
            return getDefaultValue(key);
        }
    }

    /**
     * Fournit une valeur par défaut selon le type de clé demandée
     * 
     * @param key La clé pour laquelle on demande une valeur par défaut
     * @return La valeur par défaut correspondant au type de donnée associé à la clé
     */
    private Object getDefaultValue(String key) {
        return switch (key) {
            case "score", "nbGamePlayed" -> 0;    // Valeurs numériques initialisées à 0
            case "symbol" -> "";                  // Chaîne vide pour les symboles
            default -> null;                      // Valeur null par défaut pour les autres types
        };
    }

    /**
     * Analyse et convertit la valeur lue selon le type attendu pour la clé
     * 
     * @param key La clé indiquant le type de donnée
     * @param value La valeur brute lue depuis le JSON
     * @return La valeur convertie dans le type approprié
     */
    private Object parseValue(String key, Object value) {
        return switch (key) {
            case "score", "nbGamePlayed" -> value instanceof Number ? ((Number) value).intValue() : 0; // Conversion en entier
            case "symbol" -> value.toString();    // Conversion en chaîne de caractères
            default -> value;                     // Aucune conversion pour les autres types
        };
    }

    /**
     * Écrit une valeur dans le fichier JSON pour une entité et une clé spécifiques
     * 
     * @param name Identifiant de l'entité (généralement le nom du joueur)
     * @param key Clé sous laquelle stocker la valeur
     * @param input Valeur à stocker
     */
    public void write(String name, String key, Object input) {
        // Création d'un Gson avec formatage pour une meilleure lisibilité du fichier
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Map<String, Object>> data = new HashMap<>();

        // Chargement des données existantes pour les préserver
        try (Reader reader = new FileReader(path)) {
            Type type = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
            Map<String, Map<String, Object>> existingData = gson.fromJson(reader, type);
            if (existingData != null) {
                data.putAll(existingData);
            }
        } catch (Exception ignored) {
            System.err.println("Fichier introuvable");
        }

        // Récupération ou création de la map pour l'entité spécifiée
        Map<String, Object> player_data = data.getOrDefault(name, new HashMap<>());
        // Mise à jour de la valeur pour la clé spécifiée
        player_data.put(key, input);
        // Mise à jour de la map globale
        data.put(name, player_data);

        // Écriture des données mises à jour dans le fichier JSON
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: modif le type object en string et le parser en int si nécessaire
    //TODO:
}