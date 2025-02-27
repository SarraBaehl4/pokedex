import org.json.simple.JSONObject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeighAssendingOrder {
    public static void main(String[] args) {
        // Récupérer la liste des Pokémons depuis l'API
        List<JSONObject> pokemonList = PokemonAPI.getPokemonList();

        // Trier la liste par ordre croissant de poids
        Collections.sort(pokemonList, new Comparator<JSONObject>() { //on utilise Collections.sort et comparator pour faire le tri
            @Override
            public int compare(JSONObject pokemon1, JSONObject pokemon2) {
                String weightStr1 = (String) pokemon1.get("weight");
                String weightStr2 = (String) pokemon2.get("weight");

                double weight1 = weightStr1 != null ? Double.parseDouble(weightStr1.replace("kg", "").trim()) : 0;
                double weight2 = weightStr2 != null ? Double.parseDouble(weightStr2.replace("kg", "").trim()) : 0;

                return Double.compare(weight1, weight2);
            }
        });

        // Afficher les noms des Pokémon par ordre croissant de poids
        System.out.println("Classement par ordre croissant de poids :");
        for (JSONObject pokemon : pokemonList) {
            String weightStr = (String) pokemon.get("weight");
            System.out.println(pokemon.get("name") + " : " + weightStr);
        }
    }
}
