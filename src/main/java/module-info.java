module se.selimkose.labb3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens se.selimkose.labb3 to javafx.fxml;
    exports se.selimkose.labb3;


    exports se.selimkose.labb3.Model;
    opens se.selimkose.labb3.Model to javafx.fxml;
    exports se.selimkose.labb3.Controller;
    opens se.selimkose.labb3.Controller to javafx.fxml;
    exports se.selimkose.labb3.Model.Shape;
    opens se.selimkose.labb3.Model.Shape to javafx.fxml;
}