package invest.portefeuille;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static javafx.application.Application.launch;

public class TestApi extends Application {
    private XYChart.Series<Number, Number> bitcoinSeries;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bitcoin Real-Time Chart");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Bitcoin Value");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Bitcoin Real-Time Chart");

        bitcoinSeries = new XYChart.Series<>();
        bitcoinSeries.setName("Bitcoin Value");

        lineChart.getData().add(bitcoinSeries);

        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Utilisation de ScheduledExecutorService pour effectuer des tâches périodiques
       /* ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::generateRealTimeData, 0, 30, TimeUnit.SECONDS);
           */
    }

    {
        // Remplacez ces noms de crypto-monnaie par ceux que vous souhaitez obtenir
        String[] coinNames = {"bitcoin", "ethereum", "litecoin"};

        for (String coinName : coinNames) {
            // Construire l'URL de l'API CoinGecko
            String apiUrl = "https://api.coingecko.com/api/v3/coins/" + coinName;
            URL url = null;
            try {
                url = new URL(apiUrl);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }

            // Ouvrir une connexion HTTP
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException ex) {
                throw new RuntimeException(ex);
            }

            // Lire la réponse
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder response = new StringBuilder();
            String line;

            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                response.append(line);
            }

            try {
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

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
            try {
                Thread.sleep(60000); // Délai en millisecondes (60 000 millisecondes = 1 minute)
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
