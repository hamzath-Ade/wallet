module invest.portefeuille {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens invest.portefeuille to javafx.fxml;
    exports invest.portefeuille;
}