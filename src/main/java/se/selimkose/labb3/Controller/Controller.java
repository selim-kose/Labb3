package se.selimkose.labb3.Controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.*;
import se.selimkose.labb3.Model.Shape.*;


import java.io.IOException;
import java.net.URL;
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
        choiceBoxShape.getItems().addAll(shapeModel.getShapeTypeList());
        choiceBoxShape.valueProperty().bindBidirectional(shapeModel.currentShapeTypeProperty());
        colorPicker.valueProperty().bindBidirectional(shapeModel.currentColorProperty());
        sizeSlider.valueProperty().bindBidirectional(shapeModel.currentSizeProperty());


        chatModel.getObservableShapeList().addListener((ListChangeListener.Change<?> change) -> {render();
            for(Shape i: chatModel.getObservableShapeList()){
                System.out.println(i);
            };});


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
        chatModel.renderShapes(graphicsContext);

    }


    public void redo() {
        shapeModel.redo();
        render();
    }

    public void clearCanvas() {
        shapeModel.getShapesList().clear();
        chatModel.getObservableShapeList().clear();
        render();

    }


    public void canvasAction(MouseEvent mouseEvent) {
        Position mousePosition = new Position((int) mouseEvent.getX(), (int) mouseEvent.getY());

        if (mouseEvent.getButton().name().equals("PRIMARY")) {
            Shape shape = Shape.createShape(shapeModel.getCurrentShapeType(), mousePosition, shapeModel.getCurrentColor(), shapeModel.getCurrentSize());
            shapeModel.add(shape);

        } else if (mouseEvent.getButton().name().equals("SECONDARY")) {
            shapeModel.getShapesList().stream()
                    .filter(shape -> shape.isInside(mouseEvent.getX(), mouseEvent.getY()))
                    .findFirst()
                    .ifPresent(shape -> {
                        shape.setColor(shapeModel.getCurrentColor());
                        shape.setSize(shapeModel.getCurrentSize());
                    });
        } else if (mouseEvent.getButton().name().equals("MIDDLE")) {
            Shape shape = Shape.createShape(shapeModel.getCurrentShapeType(), mousePosition, shapeModel.getCurrentColor(), shapeModel.getCurrentSize());

            chatModel.setMessage(shape.drawSVGSend());
            System.out.println(shape.drawSVGSend());
            sendMessage();
        }
        render();
    }

    public void exit() {
        System.exit(0);
    }

    public void saveSVG() throws IOException {
        Save.saveSVG(canvas, shapeModel);
    }

    public void sendMessage() {
        chatModel.sendMessage();
    }


}



