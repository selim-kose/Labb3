package se.selimkose.labb3.Model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.selimkose.labb3.Model.Shape.Shape;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    static FileChooser fileChooser = new FileChooser();

    public static void save(Canvas canvas, ShapeModel shapeModel) throws IOException {
        FileWriter fileWriter;
        if (fileChooser.getExtensionFilters().isEmpty()) {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SVG", "*.SVG"), new FileChooser.ExtensionFilter("jpg", "*.jpg"));
        }
        File filepath = fileChooser.showSaveDialog(new Stage());
        fileWriter = new FileWriter(filepath);
        if (filepath.getName().endsWith(".jpg")) {
            saveJpg(filepath, canvas);
        } else {
            saveSvg(filepath, fileWriter, canvas, shapeModel);
        }
    }

    public static void saveSvg(File filepath, FileWriter fileWriter, Canvas canvas, ShapeModel shapeModel) throws IOException {
        if (filepath != null) {
            fileWriter.write("<?xml version=\"1.0\" standalone=\"no\"?>\n" + "<svg width=\""
                    + canvas.getWidth() + "\" height=\"" + canvas.getHeight() + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "\n");
            for (Shape shape : shapeModel.shapesList) {
                shape.drawSVG(fileWriter);
            }
            fileWriter.append("</svg>");
            fileWriter.close();
        }
    }

    public static void saveJpg(File filepath, Canvas canvas) throws IOException {
        if (filepath != null) {
            WritableImage image = canvas.snapshot(null, null);
            //https://mvnrepository.com/artifact/org.openjfx/javafx-swing/11-ea+24
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filepath);
        }
    }
}

