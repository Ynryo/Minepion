package fr.ynryo.tictactoe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonManipulator {
    private String path;
    public JsonManipulator(String path) {
        this.path = path;
    }

    public int readScore(String input) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {
            Type type = new TypeToken<Map<String, Map<String, Integer>>>(){}.getType();
            Map<String, Map<String, Integer>> data = gson.fromJson(reader, type);
            if (data == null) return 0;
            Map<String, Integer> infos = data.get(input);
            if (infos == null) return 0;
            Integer score = infos.get("score");
            return score != null ? score : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void writeScore(Player player, Object input) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Map<String, Object>> data = new HashMap<>();

        // Charger les données existantes
        try (Reader reader = new FileReader(path)) {
            Type type = new TypeToken<Map<String, Map<String, Object>>>(){}.getType();
            Map<String, Map<String, Object>> existingData = gson.fromJson(reader, type);
            if (existingData != null) {
                data.putAll(existingData);
            }
        } catch (Exception ignored) {
            // Fichier absent, première écriture
        }

        Map<String, Object> player_data = new HashMap<>();
        player_data.put("score", input); // On pose ici la valeur correcte du score
        data.put(player.getName(), player_data);

        try (Writer writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}