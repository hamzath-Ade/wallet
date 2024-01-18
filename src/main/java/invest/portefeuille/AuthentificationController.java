package invest.portefeuille;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Label labelError2;
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
    private Button saveButton;


    @FXML
    public void connexion() throws IOException {
        String registeredUser = "Compte.csv";
        String email = textFieldEmailConnexion.getText();
        String password = textFieldPasswordConnexion.getText();
        Label label =labelError;
        //Créer une liste qui va reprendre tout les données du fichier CSV
        List<String> existingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(registeredUser))) {
            // Lire les lignes existantes du fichier CSV
            String line;
            while ((line = reader.readLine()) != null) {
                existingLines.add(line);
            }
        }
        //Créer un booléen qui va permettre de vérifier si le mdp et l'email existe dans la base de données
        boolean userExists = existingLines.stream().anyMatch(line -> line.contains(email +";"+ password ));
        //si vrai, on va pouvoir ouvrir la prochaine page qui est Dashboard
        if (userExists) {
            FXMLLoader fxmlLoader = new FXMLLoader(AuthentificationController.class.getResource("dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),  320, 240);

            Main.authentification.setTitle("Votre compte");
            Main.authentification.setScene(scene);
            Main.authentification.show();
        }
        //si faux, on a un message d'erreur
        else{
            label.setText("Les informations que vous avez fournies sont incorrectes. Veuillez réessayer !");
        }
    }
    @FXML
    public void inscription() throws IOException {
        String registeredUser = "Compte.csv";
        String nom = textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String email = textFieldEmailInscription.getText();
        String emailConfirm = textFieldEmailConfirmInscription.getText();
        String password = textFieldPasswordInscription.getText();
        String passwordConfirm = textFieldPasswordConfirmInscription.getText();
        String cleApi = textFieldCleApi.getText();
        List<String> existingLines = new ArrayList<>();

        // Charger les anciennes lignes du fichier CSV s'il existe déjà
        if (new File(registeredUser).exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(registeredUser))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    existingLines.add(line);
                }
            }
        }

        // Créer un booléen qui va permettre de voir si l'email est déjà dans le fichier
        boolean emailExists = existingLines.stream().anyMatch(line -> line.contains(email));

        // Si l'email existe, envoyer un message d'erreur
        if (emailExists) {
            labelError2.setText("Cette adresse e-mail est déjà utilisée");
        }
        else {
            // Si l'un des champs n'est pas rempli, envoyer un message d'erreur
            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || emailConfirm.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || cleApi.isEmpty()) {
                labelError2.setText("Tous les champs doivent être remplis.");
            }
            // Sinon, vérifier si les mots de passe et les emails sont corrects
            else if (!Objects.equals(password, passwordConfirm) || !Objects.equals(email, emailConfirm)) {
                labelError2.setText("Les mots de passe ou les emails ne correspondent pas.");
            }
            // Ajouter la nouvelle ligne avec le nouvel utilisateur
            else {
                String newUser = nom + ";" + prenom + ";"
                        + email + ";" + password + ";" + cleApi;
                existingLines.add(newUser);
                labelError2.setText("Votre compte a bien été créé!");

                // Écrire toutes les lignes dans le fichier CSV
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(registeredUser))) {
                    for (String line : existingLines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }
    }


    @FXML
    private void saveButtonClicked() {
        try {
            inscription();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
