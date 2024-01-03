package invest.portefeuille;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinGeckoApi {

    public static void main(String[] args) {
        try {
            // Remplacez ces noms de crypto-monnaie par ceux que vous souhaitez obtenir
            String[] coinNames = {"bitcoin", "ethereum", "litecoin"};

            for (String coinName : coinNames) {
                // Construire l'URL de l'API CoinGecko
                String apiUrl = "https://api.coingecko.com/api/v3/coins/" + coinName;
                URL url = new URL(apiUrl);

                // Ouvrir une connexion HTTP
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Lire la réponse
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Convertir la réponse JSON en objet JSON
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Récupérer les informations nécessaires
                String coinSymbol = jsonResponse.getString("symbol");
                double coinPrice = jsonResponse.getJSONObject("market_data").getJSONObject("current_price").getDouble("usd");

                // Afficher les informations
                System.out.println("Crypto-monnaie : " + coinName);
                System.out.println("Symbole : " + coinSymbol);
                System.out.println("Prix en USD : " + coinPrice);

                // Attendre une minute avant de faire la prochaine requête
                Thread.sleep(60000); // Délai en millisecondes (60 000 millisecondes = 1 minute)
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
