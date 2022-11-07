package se.selimkose.labb3.Model.Shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;
import se.selimkose.labb3.Model.ShapeModel;

import java.io.FileWriter;

public abstract class Shape {

    private Position position;
    private Color color;
    private double size;
    private String type;


    public Shape(Position position, Color color, double size) {
        this.position = position;
        this.color = color;
        this.size = size;
    }

    public String drawSVGSend(){
        return "<" + getType() + "cx=\"" + getPosition().x() + "\" cy=\"" + getPosition().y() + "\" r=\"" + getSize() / 2 + "\" fill=\"#" + convertColorToHex(getColor()) + "\"/>";
    }

    public static Shape createShape(ShapeType shapeType,Position position,Color color, double size){

        return switch (shapeType){
            case CIRCLE -> new Circle(position,color,size);
            case RECTANGLE -> new Rectangle(position,color,size);

        };
    }

    public abstract boolean isInside(double x, double y);

    public abstract void drawCanvas(GraphicsContext graphicsContext);

    public abstract void drawSVG(FileWriter fileWriter);

    public abstract String convertColorToHex(Color currentColor);

    public abstract void convertFromSvgToCanvas(String svgFormat);


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

    public void setSize(double size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "position=" + position +
                ", color=" + color +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}
