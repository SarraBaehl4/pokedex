import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NbOfPokemons {
    public static void main(String[] args) {
        // Récupérer la liste des Pokémon depuis l'API
        JSONArray pokemonList = PokemonAPI.getPokemonList();

        // Vérifier si la récupération a réussi
        if (pokemonList != null) {
            System.out.println("Nombre total de Pokémon : " + pokemonList.size());
        } else {
            System.out.println("Erreur lors de la récupération des Pokémon.");
        }
    }
}
