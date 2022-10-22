package se.selimkose.labb3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import se.selimkose.labb3.Model.Circle;
import se.selimkose.labb3.Model.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeometriChatApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(GeometriChatApplication.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());




        stage.getIcons().add(new Image(getClass().getResourceAsStream("geometry.png")));
        stage.setTitle("Geometri Chat");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {



        launch(GeometriChatApplication.class);
    }
}