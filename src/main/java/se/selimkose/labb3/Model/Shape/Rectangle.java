package se.selimkose.labb3.Model.Shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;

public class Rectangle extends Shape {

    private String type;

    public Rectangle(Position position, Color color, double size) {
        super(position, color, size);
        this.type = "rect";
    }


    public void draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(getColor());
        graphicsContext.fillRect(getPosition().x(),getPosition().y(),getSize(),getSize());

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
