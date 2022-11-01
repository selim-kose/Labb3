package se.selimkose.labb3.Model.Shape;

import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;
import se.selimkose.labb3.Model.ShapeModel;

public class Shape {

    private Position position;
    private Color color;
    private double size;
    private String type;


    public Shape(Position position, Color color, double size) {
        this.position = position;
        this.color = color;
        this.size = size;
    }

    public static Shape createShape(ShapeType shapeType,Position position,Color color, double size){

        return switch (shapeType){
            case CIRCLE -> new Circle(position,color,size);
            case RECTANGLE -> new Rectangle(position,color,size);

        };
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public String getType() {
        return type;
    }


}
