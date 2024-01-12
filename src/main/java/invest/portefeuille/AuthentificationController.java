package invest.portefeuille;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationController {
    private TextField textFielEmail;
    private TextField textFieldPassword;
    private Label labelError;
    public void connexion(){
        String registeredUser = "C:/Users/mathu/OneDrive/Documents/ISEP/algorithmique et programmation/Wallet/Compte.csv";
        String email = textFielEmail.getText();
        String password = textFieldPassword.getText();
        Label label =labelError;
        List<String> existingLines = new ArrayList<>;

        try (BufferedReader reader = new BufferedReader(new FileReader(registeredUser))) {
            // Lire les lignes existantes du fichier CSV
            String line;
            while ((line = reader.readLine()) != null) {
                existingLines.add(line);
            }
        }
        boolean userExists = existingLines.stream().anyMatch(line -> line.contains(email +";"+ password ));
        if (userExists) {
            FXMLLoader fxmlLoader = new FXMLLoader(AuthentificationController.class.getResource("authentification.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),  320, 240);

    }
        else{
            label.setText("Les informations que vous avez fournies sont incorrectes. Veuillez réessayer !");
        }

}
