package se.selimkose.labb3.Model.Shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;


public class Circle extends Shape{
    private String type;
    private ShapeType shapeType;



    public Circle(Position position, Color color, double size) {
        super(position, color, size);
        this.type = "circle ";
    }

    public Circle(Position position,Color color, int size){
        super(position,color,size);
        this.shapeType = ShapeType.CIRCLE;
    }


    public void draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillOval(getPosition().x(),getPosition().y(),getSize(),getSize());

    }


    @Override
    public boolean isInside(double x, double y) {
        double dx = x - getPosition().x();
        double dy = y - getPosition().y();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;

        return distanceFromCircleCenterSquared < getSize()*getSize();
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
