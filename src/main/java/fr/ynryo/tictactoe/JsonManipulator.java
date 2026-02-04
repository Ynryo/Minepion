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

public class JsonManipulator {
    private final String path;

    public JsonManipulator(String path) {
        this.path = path;
    }

    public Object read(String name, String key) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {
            Type type = new TypeToken<Map<String, Map<String, Object>>>() {
            }.getType();
            Map<String, Map<String, Object>> data = gson.fromJson(reader, type);

            if (data == null || !data.containsKey(name)) {
                return getDefaultValue(key);
            }

            Object value = data.get(name).get(key);
            if (value == null) {
                return getDefaultValue(key);
            }

            return parseValue(key, value);

        } catch (Exception e) {
            e.printStackTrace();
            return getDefaultValue(key);
        }
    }

    private Object getDefaultValue(String key) {
        return switch (key) {
            case "score", "nbGamePlayed" -> 0; // Valeurs numériques initialisées à 0
            case "symbol" -> ""; // Chaîne vide pour les symboles
            default -> null; // Valeur null par défaut pour les autres types
        };
    }

    private Object parseValue(String key, Object value) {
        return switch (key) {
            case "score", "nbGamePlayed" -> value instanceof Number ? ((Number) value).intValue() : 0; // Conversion en
                                                                                                       // entier
            case "symbol" -> value.toString(); // Conversion en chaîne de caractères
            default -> value; // Aucune conversion pour les autres types
        };
    }

    public void write(String name, String key, Object input) {
        // Création d'un Gson avec formatage pour une meilleure lisibilité du fichier
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Map<String, Object>> data = new HashMap<>();

        // Chargement des données existantes pour les préserver
        try (Reader reader = new FileReader(path)) {
            Type type = new TypeToken<Map<String, Map<String, Object>>>() {
            }.getType();
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

    // TODO: modif le type object en string et le parser en int si nécessaire
    // TODO:
}