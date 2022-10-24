package se.selimkose.labb3.Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Position;

public class Rectangle extends Shape{
    public Rectangle(Position position, Color color, Canvas canvas, GraphicsContext graphicsContext) {
        super(position, color, canvas, graphicsContext);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(color);
        graphicsContext.fillRect(position.x()-50, position.y()-50, 100,100);
    }
}
