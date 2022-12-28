module com.example.so_tp_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens com.so.tp.fx to javafx.fxml;
    exports com.so.tp.fx;
    exports controllers;
    opens controllers to javafx.fxml;
}