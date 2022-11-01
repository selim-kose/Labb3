package se.selimkose.labb3.Model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.selimkose.labb3.Controller.Controller;
import se.selimkose.labb3.Model.Shape.Rectangle;
import se.selimkose.labb3.Model.Shape.Shape;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
   static FileChooser fileChooser = new FileChooser();


    public static void saveToJPG(Canvas canvas) {
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("jpg", "*.jpg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File filepath = fileChooser.showSaveDialog(new Stage());
        WritableImage image = canvas.snapshot(null, null);

        if (filepath != null) {
            try {
                //https://mvnrepository.com/artifact/org.openjfx/javafx-swing/11-ea+24
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filepath);
            } catch (IOException e) {
                // TODO: handle exception here
            }
        }
    }

    public static void saveSVG(Canvas canvas,ShapeModel shapeModel) throws IOException {
        FileWriter fileWriter;
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SVG", "*.SVG");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
        fileChooser.getExtensionFilters().add(extensionFilter);
        File filepath = fileChooser.showSaveDialog(new Stage());
        try {
            fileWriter = new FileWriter(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (filepath != null) {
            fileWriter.write("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                    "<svg width=\"" + canvas.getWidth() + "\" height=\"" + canvas.getHeight() + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "\n");

            for (Shape s : shapeModel.getObservableShapeList()) {
                String color = s.getColor().toString().substring(2, 8);
                if (s instanceof Rectangle) {
                    fileWriter.append("<" + s.getType() + " x=\"" + s.getPosition().x() + "\" y=\"" + s.getPosition().y() + "\" width=\"" + s.getSize() + "\" height=\"" + s.getSize() + "\" fill=\"#" + color + "\" stroke-width=\"5\"/>\n");
                } else {
                    fileWriter.append("<" + s.getType() + "cx=\"" + s.getPosition().x() + "\" cy=\"" + s.getPosition().y() + "\" r=\"" + s.getSize() / 2 + "\" fill=\"#" + color + "\"/>\n");
                }
            }
            fileWriter.append("</svg>");
            fileWriter.close();
        }
    }
}
