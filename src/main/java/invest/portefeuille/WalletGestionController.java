package invest.portefeuille;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.poi.ss.usermodel.*;

public class WalletGestionController implements Initializable {

    @FXML
    Label afficheMessage;
    @FXML
    TextField textFieldNomWallet;
    @FXML
    TextField textFieldDescription;

    public WalletGestion wallet;

    public boolean existWallet = false;
    public static List<String> mesWallet = new ArrayList<>();
    // Création d'une liste de couples
    public List<Pair<String, String>> pairCryptoPrix = new ArrayList<>();

    @Override
    public void initialize(URL urll, ResourceBundle resourceBundle) {

        try {
            String apiKey = "coinranking47b4fd6e60c7f67669e1e6a0eb59c257e12baf5e9bec70a3";
            URL url = new URL("https://api.coinranking.com/v2/coins"/*apiKey=" + apiKey*/);
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

            //System.out.println(response.toString());
            //System.out.println(jsonObject);

            JSONArray coinsArray = data.getJSONArray("coins");

            for (int i = 0; i < coinsArray.length(); i++) {
                JSONObject coinsObject = coinsArray.getJSONObject(i);
                String name = coinsObject.getString("name");
                String price = coinsObject.getString("price");
                //System.out.println(name + " " + price);
                pairCryptoPrix.add(Pair.of(name, price));

            }
            System.out.println(pairCryptoPrix);



        } catch (Exception e) {
            e.printStackTrace();
        }




    }

@FXML
    public void creer() {
    String nomWallet = textFieldDescription.getText();
    String description = textFieldDescription.getText();

    wallet = new WalletGestion(nomWallet, description);
    mesWallet.add(nomWallet);

    afficheMessage.setText("Wallet créé avec succès");
    existWallet = true;
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
