module library.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    exports application;
    opens application to javafx.fxml, javafx.graphics;
}