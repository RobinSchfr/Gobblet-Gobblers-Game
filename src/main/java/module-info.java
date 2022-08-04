module com.example.gobblet_gobblers_game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.gobblet_gobblers_game to javafx.fxml;
    exports com.example.gobblet_gobblers_game;
}