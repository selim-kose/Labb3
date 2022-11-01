package se.selimkose.labb3.Model.Shape;

import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Position;

public class CircleBuilder {
    private Position position;
    private Color color;
    private double size;

    public CircleBuilder setPosition(Position position) {
        this.position = new Position(position.x(), position.y());
        return this;
    }

    public CircleBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public CircleBuilder setSize(double size) {
        this.size = size;
        return this;
    }

    public Circle createCircle() {
        return new Circle(position, color, size);
    }
}