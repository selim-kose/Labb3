package se.selimkose.labb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import se.selimkose.labb3.Model.Circle;
import se.selimkose.labb3.Model.Shape;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String view;
    private String shape;
    private String size;

    @FXML
    private ChoiceBox<String> choiceBoxSize;
    @FXML
    private ChoiceBox<String> choiceBoxShape;
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button test;



    @FXML
    public Canvas canvas = new Canvas();

    public GraphicsContext graphicsContext;


    List<String> sizeList = List.of("small", "medium", "large");
    List<String> shapeList = List.of("Circle", "Rectangle", "Line", "Triangle");


    //TODO Set values for choisebox
    //TODO save files svg format
    //TODO undo function

    public void drawCircle() {

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(0, 0, 100, 100);
    }

    public void drawCircle(Position position) {

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(position.x(), position.y(), 100, 100);
    }

    public void drawRectangle(Position position) {




        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(position.x(), position.y(), 200, 200);


    }

    public void drawLine(Position position) {

        graphicsContext.setFill(Color.GREEN);
        graphicsContext.strokeLine(position.x(), position.y(), 100, 300);
    }

    public void drawTriangle() {

        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillPolygon(new double[]{100, 150, 50}, new double[]{100, 150, 150}, 3);
    }

    public void setView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();

        view = "Test.fxml";

    }

    public void setSize() {
        this.size = choiceBoxSize.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphicsContext = canvas.getGraphicsContext2D();
        choiceBoxSize.getItems().addAll(sizeList);
        choiceBoxShape.getItems().addAll(shapeList);


        canvas.setOnMouseDragged(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(x, y, 5, 5);
        });


    }

    public void canvasMousePosition(MouseEvent mouseEvent) {
        Position mousePosition = new Position(mouseEvent.getX(), mouseEvent.getY());

        switch (this.shape) {
            case "Circle" -> new Circle(mousePosition,Color.RED,canvas,graphicsContext);
            case "Rectangle" -> drawRectangle(mousePosition);
            case "Line" -> drawLine(mousePosition);
            case "Triangle" -> drawTriangle();
        }
//drawCircle(mousePosition);

    }

    public void setShape() {
        this.shape = choiceBoxShape.getValue();
    }
}