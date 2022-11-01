package se.selimkose.labb3.Model.Shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;
import se.selimkose.labb3.Model.ShapeBuilder;


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



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void buildCirlce(MouseEvent mouseEvent){
        ShapeBuilder shapeBuilder = new ShapeBuilder();



    }
}
