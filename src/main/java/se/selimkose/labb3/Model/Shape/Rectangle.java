package se.selimkose.labb3.Model.Shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;

import java.io.FileWriter;
import java.io.IOException;

public class Rectangle extends Shape {

    private String type;
    private String svg;

    public Rectangle(Position position, Color color, double size) {
        super(position, color, size);
        this.type = "rect";
        this.svg = drawSVGSend();
    }

    //Method for drawing in canvas
    @Override
    public void drawCanvas(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getColor());
        graphicsContext.fillRect(getPosition().x() - (getSize() / 2), getPosition().y() - (getSize() / 2), getSize(), getSize());
    }

    @Override
    public String drawSVGSend(){
      return  "<" + getType() + " x=\"" + getPosition().x() + "\" y=\"" + getPosition().y() + "\" width=\"" + getSize() + "\" height=\"" + getSize() + "\" fill=\"#" + convertColorToHex(getColor()) + "\"/>";
    }

    @Override
    public void drawSVG(FileWriter fileWriter) {
        try {
            fileWriter.append("<" + getType() + " x=\"" + (getPosition().x() - (getSize()/2))+ "\" y=\"" + (getPosition().y() -getSize() /2 ) + "\" width=\"" + getSize() + "\" height=\"" + getSize() + "\" fill=\"#" + convertColorToHex(getColor()) + "\"/>\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertColorToHex(Color currentColor) {
        String hexColor  = getColor().toString().substring(2, 8);
        return hexColor;
    }


    //Method for collision detection.
    @Override
    public boolean isInside(double x, double y) {
        double px = x;      // point position (move with mouse)
        double py = y;

        double sx = (getPosition().x() - (getSize() / 2));    // square position
        double sy = (getPosition().y() - (getSize() / 2));
        double sw = getSize();    // and dimensions
        double sh = getSize();

        if (px >= sx &&         // right of the left edge AND
                px <= sx + sw &&    // left of the right edge AND
                py >= sy &&         // below the top AND
                py <= sy + sh) {    // above the bottom
            return true;
        }
        return false;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "type='" + type + '\'' +
                '}';
    }
}
