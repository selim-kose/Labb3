package se.selimkose.labb3.Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Position;
import se.selimkose.labb3.Size;

public class Circle extends Shape{

public Circle(Position position, Color color,double size, Canvas canvas, GraphicsContext graphicsContext){
    super(position,  color,  canvas, graphicsContext);
   graphicsContext = canvas.getGraphicsContext2D();
   graphicsContext.setFill(color);
   graphicsContext.fillOval(position.x()-(size/2), position.y()-(size/2), size,size);

}


}
