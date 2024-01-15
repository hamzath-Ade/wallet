package invest.portefeuille;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FinnhubApi {

    public static void main(String[] args) {
        try {
            //Clé API Finnhub utilisée pour la requette
            String apiKey = "cmhj8mhr01qmgvctraqgcmhj8mhr01qmgvctrar0";

            // URL de l'API de Finnhub pour obtenir les cotations de l'actif (exemple avec AAPL qui est l'action Apple)
            URL url = new URL("https://finnhub.io/api/v1/quote?symbol=AAPL&token=" + apiKey);

            // Établir la connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Lire la réponse
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Analyser la réponse JSON
            JSONObject jsonObject = new JSONObject(response.toString());

            // Extraire les données de la réponse
            double currentPrice = jsonObject.getDouble("c"); // "c" représente le prix actuel
            long timestamp = jsonObject.getLong("t"); // "t" représente le timestamp

            // Afficher les données
            System.out.println("Prix actuel : " + currentPrice);
            System.out.println("Timestamp : " + timestamp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
