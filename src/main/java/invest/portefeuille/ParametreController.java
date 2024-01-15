package invest.portefeuille;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParametreController {

    /* @FXML
    private Label email, nom, prenom, confirmationmdp, nouveaumdp, ancienmdp;

    @FXML
    private TextField textFieldNom, textFieldPrenom, textFieldEmail,
            textFieldAncienMdp, textFieldNouveauMdp, textFieldConfirmationMdp;

    @FXML
    private Button back, valider;

    @FXML
    private TitledPane parametre;

    // Méthode pour le bouton retour
    @FXML
    public void pageprecedente() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) back).getScene().getWindow(); // Obtient la fenêtre actuelle
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour le bouton valider
    @FXML
    public void savenewinformation() throws IOException {
        String nomUtilisateur = textFieldNom.getText();
        String prenomUtilisateur = textFieldPrenom.getText();
        String emailUtilisateur = textFieldEmail.getText();
        String nouveauMdp = textFieldNouveauMdp.getText();
        String confirmationMdp = textFieldConfirmationMdp.getText();

        if (nouveauMdp.equals(confirmationMdp)) {
            {
                saveUserInformationToCSV(nomUtilisateur, prenomUtilisateur, emailUtilisateur, nouveauMdp);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Informations enregistrées avec succès.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Validation");
            alert.setHeaderText(null);
            alert.setContentText("Les mots de passe ne correspondent pas. Veuillez réessayer.");
            alert.showAndWait();
        }
    }

    private void saveUserInformationToCSV(String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String nouveauMdp) {

    }

    @FXML


    private void updatePasswordInCSV(String email, String nouveauMdp) throws IOException {
        String csvFilePath = "C:/Users/elena/IdeaProjects/wallet/Compte.csv";
        File tempFile = new File(csvFilePath + ".tmp");
        File originalFile = new File(csvFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(","); // Divise la ligne en un tableau
                if (data.length > 2 && data[2].equals(email)) { // Vérifie si c'est la bonne ligne
                    data[3] = nouveauMdp; // Remplace le mot de passe
                    currentLine = String.join(",", data);
                }
                writer.write(currentLine);
                writer.newLine();
            }
        }

        if (!tempFile.renameTo(originalFile)) {
            throw new IOException("Could not update the original file with the new data.");
        }
    }*/
    @FXML
    Label labelInfo;
    @FXML
    private TextField textFieldNom, textFieldPrenom, textFieldEmail, textFieldOldPassword, textFieldNewPassword,textFieldNewPasswordConfirm;
    @FXML
    public void parametreEnregistrer(){
        String registeredUser = "C:/Users/mathu/IdeaProjects/wallet/Compte.csv";
        String nom=textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String email = textFieldEmail.getText();
        String ancienPassword = textFieldOldPassword.getText();
        String nouveauPassword = textFieldNewPassword.getText();
        String nouveauPasswordConfirm = textFieldNewPasswordConfirm.getText();
        Label label =labelInfo;
        List<String> existingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(registeredUser))) {
            // Lire les lignes existantes du fichier CSV
            String line;
            while ((line = reader.readLine()) != null) {
                existingLines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean userExists = existingLines.stream().anyMatch(line -> line.contains(nom + ";" + prenom + ";" + email));
        if (userExists) {

        }


    }

}
