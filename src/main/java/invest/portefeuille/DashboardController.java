package invest.portefeuille;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class DashboardController implements Initializable {

    @FXML
    private LineChart<String,Double>  diagramview;
    @FXML
    ListView listView;

    List<String> mesWallet = WalletGestionController.mesWallet ;
    ObservableList<String> items = FXCollections.observableArrayList();

  @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      items.addAll(mesWallet);
      //System.out.println(items);

      // Création de la vue de la liste
      //listView = new ListView<>(items);
      listView.setItems(items);
    }

    @FXML
    protected void AffichageButton() {
        // defining a series
        XYChart.Series<String,Double> series = new XYChart.Series<>();

        try {
            String apiKey = "coinranking47b4fd6e60c7f67669e1e6a0eb59c257e12baf5e9bec70a3";
            URL url = new URL("https://api.coinranking.com/v2/coin/Qwsogvtv82FCd/history?timePeriod=1y"/*apiKey=" + apiKey*/);
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

            System.out.println(response.toString());
            System.out.println(jsonObject);

            JSONArray historyArray = data.getJSONArray("history");

            for (int i = 0; i < historyArray.length(); i++) {
                JSONObject historyObject = historyArray.getJSONObject(i);
                String price = historyObject.getString("price");
                long timestamp = historyObject.getLong("timestamp");

                // Convertir la chaîne de caractères en Double
                double priceValue = Double.parseDouble(price);

                // Convertir le timestamp en LocalDateTime
                LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());

                // Formatter la date au format HH:mm:ss
                String formattedTime = dateTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));

                // Ajouter la paire de valeurs au graphique
                series.getData().add(new XYChart.Data<String,Double>( formattedTime,priceValue));
            }


            // Déplacer l'ajout de la série en dehors de la boucle for
            diagramview.getData().add(series);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void moreInformations(){


    }
@FXML
    public void creerWallet() {

    try {
        // Charge le fichier FXML de la page precedente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("walletGestion.fxml"));
        Parent root = loader.load();

        // Crée une nouvelle scène
        Scene nouvelleScene = new Scene(root);

        Main.authentification.setScene(nouvelleScene);

    } catch (IOException e) {
        e.printStackTrace();

    }
    }


}
