import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class PokemonReader {
    public static void main(String[] args) {
        try {
            // URL de l'API Pokémon
            URL url = new URL("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Parser le JSON
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(new InputStreamReader(connection.getInputStream()));
            JSONArray pokemonList = (JSONArray) data.get("pokemon");
            
            // Ordre exact des champs dans l'API Pokémon
            List<String> fieldOrder = Arrays.asList(
                "id", "num", "name", "img", "type", "height", "weight", 
                "candy", "candy_count", "egg", "spawn_chance", "avg_spawns", 
                "spawn_time", "multipliers", "weaknesses", "next_evolution", "prev_evolution"
            );
            
            // Afficher tous les Pokémon dans l'ordre exact
            for (Object obj : pokemonList) {
                JSONObject pokemon = (JSONObject) obj;
                
                // Parcourir les champs dans l'ordre défini
                for (String field : fieldOrder) {
                    if (pokemon.containsKey(field)) {
                        System.out.println(field + ": " + pokemon.get(field));
                    }
                }
                
                System.out.println("------------------");
            }
            connection.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}