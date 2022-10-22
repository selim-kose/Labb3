module com.example.labb3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.selimkose.labb3 to javafx.fxml;
    exports se.selimkose.labb3;
    exports com.example;
    opens com.example to javafx.fxml;
    exports se.selimkose.labb3.Model;
    opens se.selimkose.labb3.Model to javafx.fxml;
}