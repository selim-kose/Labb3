package se.selimkose.labb3.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Color;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.selimkose.labb3.Model.*;
import se.selimkose.labb3.Model.Shape.*;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ChatModel chatModel = new ChatModel();
    ShapeModel shapeModel = new ShapeModel();
    @FXML
    private Slider sizeSlider;
    @FXML
    private ChoiceBox<ShapeType> choiceBoxShape;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Canvas canvas = new Canvas();
    public GraphicsContext graphicsContext;
    @FXML
    public Button buttonSend;
    @FXML
    public TextField textFieldMessage;
    @FXML
    public ListView<String> messageListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphicsContext = canvas.getGraphicsContext2D();
        choiceBoxShape.getItems().addAll(shapeModel.getShapeList());
        choiceBoxShape.valueProperty().bindBidirectional(shapeModel.currentShapeTypeProperty());
        colorPicker.valueProperty().bindBidirectional(shapeModel.currentColorProperty());
        sizeSlider.valueProperty().bindBidirectional(shapeModel.currentSizeProperty());


        textFieldMessage.textProperty().bindBidirectional(chatModel.messageProperty());
        messageListView.setItems(chatModel.getObservableList());
        buttonSend.disableProperty().bind(chatModel.messageProperty().isEmpty());

    }

    public void undo() {
        shapeModel.undo();
        render();

    }

    public void render() {
        graphicsContext.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
        shapeModel.renderShapes(graphicsContext);
    }

    public void redo() {
        shapeModel.redo();
        render();
    }

    public void clearCanvas() {
        shapeModel.getShapesList().clear();
        render();

    }


    public void canvasAction(MouseEvent mouseEvent) {
        Position mousePosition = new Position((int) mouseEvent.getX(), (int) mouseEvent.getY());

        if (mouseEvent.getButton().name().equals("PRIMARY")) {
            Shape shape = Shape.createShape(shapeModel.getCurrentShapeType(), mousePosition, shapeModel.getCurrentColor(), shapeModel.getCurrentSize());
            shapeModel.add(shape);
            render();
        } else if (mouseEvent.getButton().name().equals("SECONDARY") && mouseEvent.isControlDown()) {
            shapeModel.getShapesList().stream()
                    .filter(shape -> shape.isInside(mouseEvent.getX(), mouseEvent.getY()))
                    .findFirst().ifPresent(shape -> shape.setColor(shapeModel.getCurrentColor()));
            shapeModel.renderShapes(graphicsContext);

        }
    }


    public void saveFile() {
        Save.saveToJPG(canvas);
    }

    public void saveSVG() throws IOException {
        Save.saveSVG(canvas, shapeModel);
    }

    public void sendMessage() {
        chatModel.sendMessage();
    }


}



