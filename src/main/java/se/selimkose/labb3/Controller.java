package se.selimkose.labb3;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.selimkose.labb3.Model.*;
import se.selimkose.labb3.Model.Rectangle;
import se.selimkose.labb3.Model.Shape;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ChatModel chatModel = new ChatModel();

    private Stage stage;
    private Scene scene;

    private String view;

    List<Shape> shapes = new ArrayList<>();
    List<Shape> redoShapes = new ArrayList<>();
    @FXML
    private Slider sizeSlider;
    @FXML
    private ChoiceBox<String> choiceBoxShape;
    private String shape;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Canvas canvas = new Canvas();
    public GraphicsContext graphicsContext;
    public FileChooser fileChoser = new FileChooser();

    List<String> shapeList = List.of("Circle", "Rectangle", "Line", "Triangle");


    @FXML
    public Button buttonSend;
    @FXML
    public TextField textFieldMessage;
    @FXML
    public VBox vBoxMessages;
    @FXML
    public ScrollPane scrollPane;

    @FXML
    public ListView<String> messageListView;


    //TODO Set values for choisebox
    //TODO save files svg format
    //TODO undo function

    public void redo() {

        Shape shape = redoShapes.get(redoShapes.size() - 1);


        if (shape instanceof Circle) {

            graphicsContext.setFill(shape.getColor());
            graphicsContext.fillOval(shape.getPosition().x() - 5, shape.getPosition().y() - 5, ((Circle) shape).getSize(), ((Circle) shape).getSize());

            if (redoShapes.size() >= 1) {

                redoShapes.remove(redoShapes.size() - 1);
            }

        }
        if (shape instanceof Rectangle) {

            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(shape.getPosition().x() - (((Rectangle) shape).getSize() / 2), shape.getPosition().y() - (((Rectangle) shape).getSize() / 2), ((Rectangle) shape).getSize(), ((Rectangle) shape).getSize());


            if (shapes.size() > 1) {
                redoShapes.add(shapes.get(shapes.size() - 1));
                shapes.remove(shapes.size() - 1);
            }
        }
    }

    public void undo() {

        Shape shape = shapes.get(shapes.size() - 1);


        if (shape instanceof Circle) {
            System.out.println("circle");

            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillOval(shape.getPosition().x() - (((Circle) shape).getSize() / 2), shape.getPosition().y() - (((Circle) shape).getSize() / 2), ((Circle) shape).getSize()+6, ((Circle) shape).getSize()+6);

            if (shapes.size() > 1) {
                redoShapes.add(shapes.get(shapes.size() - 1));
                shapes.remove(shapes.size() - 1);
            }

        }
        if (shape instanceof Rectangle) {
            System.out.println("Rectangle");
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(shape.getPosition().x() - (((Rectangle) shape).getSize() / 2), shape.getPosition().y() - (((Rectangle) shape).getSize() / 2), ((Rectangle) shape).getSize(), ((Rectangle) shape).getSize()+5);


            if (shapes.size() > 1) {
                redoShapes.add(shapes.get(shapes.size() - 1));
                shapes.remove(shapes.size() - 1);
            }
        }

    }


    public void clearCanvas() {
        graphicsContext.clearRect(0, 0, 730, 668);
    }


    public void drawTriangle() {

        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillPolygon(new double[]{100, 150, 50}, new double[]{100, 150, 150}, 3);
    }

    public void line() {
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


        textFieldMessage.textProperty().bindBidirectional(chatModel.messageProperty());
        messageListView.setItems(chatModel.getObservableList());
        buttonSend.disableProperty().bind(chatModel.messageProperty().isEmpty());


    }

    public void renderShapes() {
        for (Shape i : shapes) {
            graphicsContext.setFill(i.getColor());
            if (i instanceof Circle) {
                graphicsContext.fillOval(i.getPosition().x()-(i.getSize()/2), i.getPosition().y()-(i.getSize()/2), i.getSize(), i.getSize());
            } else if (i instanceof Rectangle) {
                graphicsContext.fillRect(i.getPosition().x()-(i.getSize()/2), i.getPosition().y() -(i.getSize()/2), i.getSize(), i.getSize());
            }
        }

    }


    public void canvasMousePosition(MouseEvent mouseEvent) {
        Position mousePosition = new Position((int)mouseEvent.getX(), (int)mouseEvent.getY());

        switch (shape) {
            case "Circle" -> shapes.add(new Circle(mousePosition, colorPicker.getValue(), sizeSlider.getValue()));
            case "Rectangle" -> shapes.add(new Rectangle(mousePosition, colorPicker.getValue(), sizeSlider.getValue()));
            case "Line" -> line();
            case "Triangle" -> drawTriangle();
        }
        renderShapes();
    }

    public void setShape() {
        this.shape = choiceBoxShape.getValue();
    }

    public void saveFile() {

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("jpg", "*.jpg");
        fileChoser.getExtensionFilters().add(extensionFilter);

        File filepath = fileChoser.showSaveDialog(new Stage());

        if (filepath != null) {
            WritableImage image = canvas.snapshot(null, null);
            try {

                //https://mvnrepository.com/artifact/org.openjfx/javafx-swing/11-ea+24
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filepath);
            } catch (IOException e) {
                // TODO: handle exception here
            }
        }
    }

    public void sendMessage() {
       // chatModel.getObservableList().add(chatModel.getMessage());
        chatModel.sendMessage();

        System.out.println(chatModel.getMessage());
    }

}

