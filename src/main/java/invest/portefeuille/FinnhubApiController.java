package invest.portefeuille;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FinnhubApiController {

    @FXML
    private Label labelPrixActif1;

    @FXML
    private Label labelPrixActif2;

    @FXML
    private Label labelPrixActif3;

    public void initialize() {
        fetchAndDisplayData("AAPL", labelPrixActif1);
        fetchAndDisplayData("GOOGL", labelPrixActif2);
        fetchAndDisplayData("MSFT", labelPrixActif3);
    }

    private void fetchAndDisplayData(String symbol, Label label) {
        try {
            // Clé API Finnhub utilisée pour la requête
            String apiKey = "cmhj8mhr01qmgvctraqgcmhj8mhr01qmgvctrar0";

            // URL de l'API de Finnhub pour obtenir les cotations de l'actif
            URL url = new URL("https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + apiKey);

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

            // Analyse de la réponse JSON
            JSONObject jsonObject = new JSONObject(response.toString());

            // Extraction des données de la réponse
            double currentPrice = jsonObject.getDouble("c"); // "c" représente le prix actuel
            long timestamp = jsonObject.getLong("t"); // "t" représente le timestamp

            // Affichage des données dans le TextField
            label.setText("Prix actuel : " + currentPrice + "\nTimestamp : " + timestamp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

