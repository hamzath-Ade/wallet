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
import java.util.ArrayList;
import java.util.List;

public class NewsController {

    @FXML
    ListView listNews;
    @FXML
    Label labelRafraichissement;

    List<String> articles = new ArrayList<>();
    ObservableList<String> items = FXCollections.observableArrayList();

    public void initialize() {
        obtenirNews();

    }

    public  void obtenirNews() {

        try {
           // String apiKey = "coinranking47b4fd6e60c7f67669e1e6a0eb59c257e12baf5e9bec70a3";
            URL url = new URL("https://min-api.cryptocompare.com/data/v2/news/?lang=EN");
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

            JSONArray data = jsonObject.getJSONArray("Data");



            //JSONArray articlesCrypto = data.getJSONArray("history");

            for (int i = 0; i < data.length(); i++) {
                JSONObject articleObject = data.getJSONObject(i);
                String titre = articleObject.getString("title");
                String link = articleObject.getString("url");

                //System.out.println(titre + " " + link);
                articles.add(titre + " copier ou cliquer sur le lien suivant pour lire l'article: " + link);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

       items.addAll(articles);
        //System.out.println(items);

        // Création de la vue de la liste
        listNews.setItems(items);
    }

    @FXML
    public void Rafraichir(){
        obtenirNews();
        labelRafraichissement.setText("La liste contient les dernières actualités");
    }
    @FXML
    public void Retour() throws IOException {


        try {
            // Charge le fichier FXML de la page precedente
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
