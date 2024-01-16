package invest.portefeuille;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage authentification;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  320, 240);


        authentification = primaryStage;
        authentification.setTitle("Formulaire d'authentification");

        primaryStage.setScene(scene); // Création de la scène principale
        authentification.show();

    }
}
