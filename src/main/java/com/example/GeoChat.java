package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.IOException;

public class GeoChat extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        //  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(800, 480);
        ColorPicker colorPalette = new ColorPicker();
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Small","Medium","Large");
        choiceBox.setValue("Size");
        System.out.println(choiceBox.getSelectionModel());



        Button button = new Button("Circle");
        Button button1 = new Button("Rectangle");
        Button button2 = new Button("Line");
        Button button3 = new Button("Point");


        HBox hBox = new HBox(button, button1, button2, button3, colorPalette,choiceBox);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));



        Canvas canvas = new Canvas(600,400);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        //drawShapes(graphicsContext);
      //  drawCircle(graphicsContext);
     //   drawRectangle(graphicsContext);

        drawLine(graphicsContext);

       //borderPane.getChildren().add(canvas);
        borderPane.setTop(hBox);
       borderPane.setCenter(canvas);



        Scene scene = new Scene(borderPane);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        canvas.setOnMouseDragged(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            graphicsContext.setFill(colorPalette.getValue());
            graphicsContext.fillOval(x,y,5,5);
        });
    }

    private void drawShapes(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.setStroke(Color.RED);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeLine(40, 10, 10, 40);
        graphicsContext.fillOval(10, 60, 30, 30);
        graphicsContext.strokeOval(60, 60, 30, 30);
        graphicsContext.fillRoundRect(110, 60, 30, 30, 10, 10);
        graphicsContext.strokeRoundRect(160, 60, 30, 30, 10, 10);
        graphicsContext.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        graphicsContext.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        graphicsContext.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        graphicsContext.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        graphicsContext.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        graphicsContext.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        graphicsContext.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        graphicsContext.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        graphicsContext.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

    private void drawCircle(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(0,0,200,200);
    }

    private void drawRectangle(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(0,0,200,200);
    }

    private void drawLine(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.strokeLine(0,0,200,200);
    }



    public static void main(String[] args) {

        launch(GeoChat.class);
    }
}