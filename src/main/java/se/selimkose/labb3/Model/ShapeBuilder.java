package se.selimkose.labb3.Model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Shape.Circle;
import se.selimkose.labb3.Model.Shape.Shape;

public class ShapeBuilder {

    private Position position;
    private Color color;
    private double size;
    private String type;

    public ShapeBuilder(){}

    public ShapeBuilder setPosition(MouseEvent mouseEvent) {
        this.position = new Position((int) mouseEvent.getX(), (int)(mouseEvent.getY()));
        return this;
    }

    public ShapeBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public ShapeBuilder setSize(double size) {
        this.size = size;
        return this;
    }

    public ShapeBuilder setType(String type) {
        this.type = type;
        return this;
    }


    public Shape build(){
        return new Shape(position,color,size);
    }




}
