import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class PokemonDisplay {
    public static void main(String[] args) {
        try {
            // URL de l'API Pokémon
            URL url = new URL("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Parser le JSON
            JSONParser parser = new JSONParser();
            // 1. Récupérer le flux d'octets bruts depuis la connexion HTTP
            InputStream rawData = connection.getInputStream();
            // 2. Créer un lecteur qui convertit les octets en caractères
            // Cette étape est nécessaire car JSON est un format texte
            InputStreamReader charReader = new InputStreamReader(rawData);
            // 3. Parser (analyser) le contenu JSON pour créer une structure de données en mémoire
            // Le parser lit caractère par caractère et identifie la structure JSON
            Object parsedContent = parser.parse(charReader);
            // 4. Convertir (cast) le résultat en JSONObject puisque nous savons que le document commence par '{'
            JSONObject data = (JSONObject) parsedContent;

            //Extraire la valeur associée à la clé "pokemon" de l'objet JSON principal
            Object pokemonValue = data.get("pokemon");
            //// 2. Vérifier que la valeur n'est pas null (optionnel mais recommandé)
            if (pokemonValue == null) {
            throw new RuntimeException("La clé 'pokemon' n'existe pas dans le JSON");
            }
            // 3. Convertir la valeur en JSONArray car nous savons que c'est un tableau
            JSONArray pokemonList = (JSONArray) pokemonValue;
            
            
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