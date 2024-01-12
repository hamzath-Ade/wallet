package invest.portefeuille;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthentificationController {
    @FXML
    private TextField textFieldEmailConnexion;
    @FXML
    private TextField textFieldPasswordConnexion;
    @FXML
    private Label labelError;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldEmailInscription;
    @FXML
    private TextField textFieldEmailConfirmInscription;
    @FXML
    private TextField textFieldPasswordInscription;
    @FXML
    private TextField textFieldPasswordConfirmInscription;
    @FXML
    private TextField textFieldCleApi;
    @FXML
    public void connexion() throws IOException {
        String registeredUser = "C:/Users/mathu/IdeaProjects/wallet/Compte.csv";
        String email = textFieldEmailConnexion.getText();
        String password = textFieldPasswordConnexion.getText();
        Label label =labelError;
        List<String> existingLines = new ArrayList<>();

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

            Main.authentification.setTitle("Votre compte");
            Main.authentification.setScene(scene);
            Main.authentification.show();
    }
        else{
            label.setText("Les informations que vous avez fournies sont incorrectes. Veuillez réessayer !");
        }
    }
/*public void inscription()throws IOException{
    String registeredUser = "C:/Users/mathu/IdeaProjects/wallet/Compte.csv";
    String nom= textFieldNom.getText();
    String prenom= textFieldPrenom.getText();
    String email=textFieldEmailInscription.getText();
    String emailConfirm= textFieldEmailConfirmInscription.getText();
    String password =textFieldPasswordInscription.getText();
    String passwordConfirm =textFieldPasswordConfirmInscription.getText();
    String cleApi=textFieldCleApi.getText();
    List<String> existingLines = new ArrayList<>();
    boolean emailExists = existingLines.stream().anyMatch(line -> line.contains(email));

        if (emailExists) {
                labelError.setText("Cette adresse e-mail est déjà utilisée");
        }
        else {
            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || emailConfirm.isEmpty() || password.isEmpty()|| passwordConfirm.isEmpty() || cleApi.isEmpty()) {
                labelError.setText("Tous les champs doivent être remplis.");
            }
            else if (!Objects.equals(password, passwordConfirm)||!Objects.equals(email, emailConfirm)){
                labelError.setText("Les mots de passe ou les mails ne correspondent pas.");
            }
            else {
                // Ajouter la nouvelle ligne avec le nouvel utilisateur
                String newUser = nom + ";" + prenom + ";"
                        + email + ";" + password + ";" + cleApi;
                existingLines.add(newUser);
                labelError.setText("Votre compte a bien été crée!");

                // Écrire toutes les lignes dans le fichier CSV
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(registeredUser))) {
                    for (String line : existingLines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

        }

}*/
}
