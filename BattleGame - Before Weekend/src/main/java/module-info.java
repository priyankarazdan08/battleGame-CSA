module com.example.battlegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.battlegame to javafx.fxml;
    exports com.example.battlegame;
}