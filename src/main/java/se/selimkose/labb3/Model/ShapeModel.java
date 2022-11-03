package se.selimkose.labb3.Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Shape.Circle;
import se.selimkose.labb3.Model.Shape.Rectangle;
import se.selimkose.labb3.Model.Shape.Shape;
import se.selimkose.labb3.Model.Shape.ShapeType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ShapeModel {
    Deque<Shape> shapesList = new ArrayDeque<>();
    Deque<Shape> redoShapeList = new ArrayDeque<>();

    ObservableList<ShapeType> shapeList = FXCollections.observableArrayList(ShapeType.values());
    ObjectProperty<Color> currentColor = new SimpleObjectProperty<>(Color.RED);
    DoubleProperty currentSize = new SimpleDoubleProperty(50);
    ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty(ShapeType.CIRCLE);

    public ShapeType getCurrentShapeType() {
        return currentShapeType.get();
    }

    public ObjectProperty<ShapeType> currentShapeTypeProperty() {
        return currentShapeType;
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType.set(currentShapeType);
    }

    public double getCurrentSize() {
        return currentSize.get();
    }

    public DoubleProperty currentSizeProperty() {
        return currentSize;
    }

    public void setCurrentSize(double currentSize) {
        this.currentSize.set(currentSize);
    }

    public ObservableList<ShapeType> getShapeList() {
        return shapeList;
    }

    public void setShapeList(ObservableList<ShapeType> shapeList) {
        this.shapeList = shapeList;
    }

    public Color getCurrentColor() {
        return currentColor.get();
    }

    public ObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor.set(currentColor);
    }

    public ShapeModel() {

    }

    public void add(Shape shape) {
        shapesList.add(shape);
    }

    public Deque<Shape> getShapesList() {
        return shapesList;
    }


    public void undo() {
        if (!shapesList.isEmpty()) {
            redoShapeList.add(shapesList.removeLast());
        }
    }

    public void redo() {
        if(!redoShapeList.isEmpty()){
            shapesList.add(redoShapeList.removeLast());
        }
    }


    public void renderShapes(GraphicsContext graphicsContext) {
        for (Shape i : shapesList) {
            graphicsContext.setFill(i.getColor());
            if (i instanceof Circle) {
                graphicsContext.fillOval(i.getPosition().x() - (i.getSize() / 2), i.getPosition().y() - (i.getSize() / 2), i.getSize(), i.getSize());
            } else if (i instanceof Rectangle) {
                graphicsContext.fillRect(i.getPosition().x() - (i.getSize() / 2), i.getPosition().y() - (i.getSize() / 2), i.getSize(), i.getSize());
            }
        }

    }

}
