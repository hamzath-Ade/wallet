module invest.portefeuille {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
   // requires binance.api.client;
    requires java.net.http;
    requires org.json;
    //requires com.fasterxml.jackson.databind;

    opens invest.portefeuille to javafx.fxml;
    exports invest.portefeuille;
    exports src.main.java.invest.portefeuille;
    opens src.main.java.invest.portefeuille to javafx.fxml;
}