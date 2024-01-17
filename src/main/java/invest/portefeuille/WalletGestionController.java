package invest.portefeuille;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WalletGestionController {

    @FXML
    Label afficheMessage;
    @FXML
    TextField textFieldNomWallet;
    @FXML
    TextField textFieldDescription;

    public WalletGestion wallet;

    public boolean existWallet = false;
    public static List<WalletGestion> mesWallet = new ArrayList<>();
@FXML
    public void creer() {
    String nomWallet = textFieldDescription.getText();
    String description = textFieldDescription.getText();

    wallet = new WalletGestion(nomWallet, description);
    mesWallet.add(wallet);

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
