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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.selimkose.labb3.Model.Circle;
import se.selimkose.labb3.Model.Rectangle;
import se.selimkose.labb3.Model.Shape;
import se.selimkose.labb3.Model.ShapeStorage;


import java.io.BufferedInputStream;
import java.io.File;
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

    private String size;

    List<Shape> shapes = new ArrayList<>();
    @FXML
    private Slider sizeSlider;
    WritableImage writableImage = new WritableImage(730, 668);
    @FXML
    ImageView imageView = new ImageView(writableImage);
    @FXML
    private ChoiceBox<String> choiceBoxSize;
    @FXML
    private ChoiceBox<String> choiceBoxShape;
    private String shape;
    @FXML
    private ColorPicker colorPicker;

    @FXML
    public Canvas canvas = new Canvas();
    public GraphicsContext graphicsContext;
    public FileChooser fileChoser = new FileChooser();


    List<String> shapeList = List.of("Circle", "Rectangle", "Line", "Triangle");


    //TODO Set values for choisebox
    //TODO save files svg format
    //TODO undo function



    public void clearCanvas(){
        graphicsContext.clearRect(0,0,730,668);
    }

    public void drawLine(Position position) {



        graphicsContext.setFill(Color.GREEN);
        graphicsContext.strokeLine(position.x(), position.y(), 100, 300);
    }

    public void drawTriangle() {

        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillPolygon(new double[]{100, 150, 50}, new double[]{100, 150, 150}, 3);
    }

    public void line(){
        canvas.setOnMouseDragged(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fillOval(x, y, 5, 5);
        });

    }

    public void setView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();

        view = "Test.fxml";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphicsContext = canvas.getGraphicsContext2D();
        choiceBoxShape.getItems().addAll(shapeList);
        shape = "Circle";



    }

    public void canvasMousePosition(MouseEvent mouseEvent) {
        Position mousePosition = new Position(mouseEvent.getX(), mouseEvent.getY());

        switch (shape) {
            case "Circle" -> new Circle(mousePosition, colorPicker.getValue(),sizeSlider.getValue(), canvas, graphicsContext);
            case "Rectangle" -> new Rectangle(mousePosition, colorPicker.getValue(), canvas, graphicsContext);
            case "Line" -> line();
            case "Triangle" -> drawTriangle();

        }

    }

    public void setShape() {
        this.shape = choiceBoxShape.getValue();
    }

    public void saveFile(ActionEvent actionEvent) {

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SVG files", "*.jpg");
        fileChoser.getExtensionFilters().add(extensionFilter);

        File file = fileChoser.showSaveDialog(new Stage());

        if (file != null) {
            writableImage = new WritableImage(730, 668);
            canvas.snapshot(null, writableImage);

        }
    }

    public void save() {
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SVG files", "*.jpg");
        fileChoser.getExtensionFilters().add(extensionFilter);

        File file = fileChoser.showSaveDialog(new Stage());

        if (file != null) {
            Image snapShot = canvas.snapshot(null,null);

            var i = canvas.snapshot(null, writableImage);


        }
    }
}

