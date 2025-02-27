import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Evolution {
    public static void main(String[] args) {
        // Récupérer la liste des Pokémons depuis l'API
        JSONArray pokemonList = PokemonAPI.getPokemonList();

        for (int i = 0; i < pokemonList.size(); i++) {
            JSONObject pokemon = (JSONObject) pokemonList.get(i);
            String pokemonName = (String) pokemon.get("name");

            // Récupérer la liste des évolutions
            JSONArray nextEvolutions = (JSONArray) pokemon.get("next_evolution");

            // Vérifier si nextEvolutions est null
            if (nextEvolutions != null) {
                // Afficher les évolutions de chaque Pokémon
                System.out.print(pokemonName + " -> ");
                for (int j = 0; j < nextEvolutions.size(); j++) {
                    JSONObject evolution = (JSONObject) nextEvolutions.get(j);
                    String evolutionName = (String) evolution.get("name");
                    System.out.print(evolutionName);

                    // Ajouter une flèche si ce n'est pas la dernière évolution
                    if (j < nextEvolutions.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            } else {
                // Afficher un message si le Pokémon n'a pas d'évolution
                System.out.println(pokemonName + " -> Pas d'évolution");
            }
        }
    }
}
