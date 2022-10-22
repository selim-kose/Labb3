package se.selimkose.labb3.Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Position;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private Position position;
    private Color color;
    private Canvas canvas;
    private GraphicsContext graphicsContext;


    public Shape(Position position, Color color, Canvas canvas, GraphicsContext graphicsContext) {
        this.position = position;
        this.color = color;
        this.canvas = canvas;
        this.graphicsContext = graphicsContext;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
}
