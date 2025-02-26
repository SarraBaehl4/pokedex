import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;

public class PokemonAPI {

    // Méthode pour récupérer la liste complète des Pokémon
    public static JSONArray getPokemonList() {
        try {
            // URL de l'API Pokémon
            URL url = new URL("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Lire et parser le JSON
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(new InputStreamReader(connection.getInputStream()));
            
            // Récupérer la liste des Pokémon
            JSONArray pokemonList = (JSONArray) data.get("pokemon");

            // Fermer la connexion
            connection.disconnect();
            
            return pokemonList; // Retourner la liste complète des Pokémon

        } catch (Exception e) {
            e.printStackTrace();
            return null; // En cas d'erreur, on retourne null
        }
    }
}
