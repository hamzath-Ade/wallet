package invest.portefeuille;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinnhubApiController {

    @FXML
    private Label labelActif1;
    @FXML
    private Label labelActif2;
    @FXML
    private Label labelActif3;
    @FXML
    private Label labelPrixActif1;
    @FXML
    private Label labelPrixActif2;
    @FXML
    private Label labelPrixActif3;
    @FXML
    ListView listCryptoPrix;

    List<String> crypto = new ArrayList<>();
    ObservableList<String> items = FXCollections.observableArrayList();

    public void initialize() {

        labelActif1.setText("AAPL");
        labelActif2.setText("GOOGL");
        labelActif3.setText("MSFT");
        fetchAndDisplayData("AAPL", labelPrixActif1);
        fetchAndDisplayData("GOOGL", labelPrixActif2);
        fetchAndDisplayData("MSFT", labelPrixActif3);

        listCryptoPrix();
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

            Date date = new Date(timestamp*1000);
            // Création d'un objet SimpleDateFormat avec le format souhaité
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            // Formatage de la date
            String dateFormatee = dateFormat.format(date);

            // Affichage des données dans le TextField
            label.setText("Prix actuel : " + currentPrice + ", date de la mise à jour : " + dateFormatee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listCryptoPrix(){

        try {
            // String apiKey = "coinranking47b4fd6e60c7f67669e1e6a0eb59c257e12baf5e9bec70a3";
            URL url = new URL("https://api.coinranking.com/v2/coins?base=USD");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            JSONObject jsonObject = new JSONObject();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            jsonObject = new JSONObject(response.toString());

            JSONObject data = jsonObject.getJSONObject("data");



            JSONArray listCrypto = data.getJSONArray("coins");

            for (int i = 0; i < listCrypto.length(); i++) {
                JSONObject articleObject = listCrypto.getJSONObject(i);
                String name = articleObject.getString("name");
                String prix = articleObject.getString("price");

                //System.out.println(titre + " " + link);
                crypto.add(name + " " + prix + "$");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        items.addAll(crypto);
        //System.out.println(items);

        // Création de la vue de la liste
        listCryptoPrix.setItems(items);
    }

    public void actualiser(){
        listCryptoPrix();
    }

    public void Retour(){

        try {
            // Charge le fichier FXML de la page des actus
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            // Crée une nouvelle scène
            Scene nouvelleScene = new Scene(root);

            Main.authentification.setScene(nouvelleScene);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

