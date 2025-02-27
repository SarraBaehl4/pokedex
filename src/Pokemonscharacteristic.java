import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Pokemonscharacteristic {
    public static void main(String[] args) {
        // Récupérer la liste des Pokémons depuis l'API
        JSONArray pokemonList = PokemonAPI.getPokemonList();

        // Vérifier si la récupération a réussi
        if (pokemonList != null) {
            System.out.println("Nombre total de Pokémon : " + pokemonList.size());
        } else {
            System.out.println("Erreur lors de la récupération des Pokémon.");
        }
        //Compter et Afficher les pokémons qui pèse plus de 10kg
            int nbPokemon10Kg = 0;
            System.out.println("Pokémon de plus de 10kg :");
            
            for (int i = 0; i < pokemonList.size(); i++) {
                JSONObject pokemon = (JSONObject) pokemonList.get(i);
                String weightStr = (String) pokemon.get("weight");
                
                // Extraire la valeur numérique (supprimer "kg")
                double weight = 0;
                if (weightStr != null) {
                    weight = Double.parseDouble(weightStr.replace("kg", "").trim());
                }
                
                if (weight >= 10.0) {
                    nbPokemon10Kg++;
                    System.out.println(pokemon.get("name") + " - " + weightStr);
                }
            }
            
            System.out.println("Nombre total des pokémons pesant plus de 10kg: " + nbPokemon10Kg);
    } 
}
