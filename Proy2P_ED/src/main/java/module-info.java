module com.espol.proy2p_ed {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;

    opens com.espol.proy2p_ed to javafx.fxml;
    exports com.espol.proy2p_ed;
}
