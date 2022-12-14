package se.selimkose.labb3.Model.Shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;

import java.io.FileWriter;
import java.io.IOException;


public class Circle extends Shape {
    private String type;
    private String svg;

    public Circle(Position position, Color color, double size) {
        super(position, color, size);
        this.type = "circle ";
        this.svg = drawSVGSend();
    }

    //Method for drawing in canvas
    @Override
    public void drawCanvas(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillOval(getPosition().x() - (getSize() / 2), getPosition().y() - (getSize() / 2), getSize(), getSize());
    }

    @Override
    public void drawSVG(FileWriter fileWriter) {
        try {
            fileWriter.append("<" + getType() + "cx=\"" + getPosition().x() + "\" cy=\"" + getPosition().y() + "\" r=\"" + getSize() / 2 + "\" fill=\"#" + convertColorToHex(getColor()) + "\"/>\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String drawSVGSend(){
        return "<" + getType() + "cx=\"" + getPosition().x() + "\" cy=\"" + getPosition().y() + "\" r=\"" + getSize() / 2 + "\" fill=\"#" + convertColorToHex(getColor()) + "\"/>";
    }

    @Override
    public String convertColorToHex(Color currentColor) {
        String hexColor = getColor().toString().substring(2, 8);
        return hexColor;
    }


    //Method for collision detection.
    @Override
    public boolean isInside(double x, double y) {
        double dx = x - getPosition().x();
        double dy = y - getPosition().y();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;

        return distanceFromCircleCenterSquared < (getSize() /2) * (getSize() /2);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "type=" + type +" "+ this.getPosition().x()+" "+this.getPosition().y() +" " +this.getSize()+ " "+ this.getColor()+
                '}';
    }
}
