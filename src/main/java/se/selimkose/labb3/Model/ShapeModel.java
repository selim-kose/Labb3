package se.selimkose.labb3.Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.selimkose.labb3.Model.Shape.Shape;
import se.selimkose.labb3.Model.Shape.ShapeType;

import java.util.ArrayDeque;
import java.util.Deque;


public class ShapeModel {
    Deque<Shape> shapesList = new ArrayDeque<>();
    Deque<Shape> redoShapeList = new ArrayDeque<>();
    ObservableList<ShapeType> shapeTypeList = FXCollections.observableArrayList(ShapeType.values());
    ObjectProperty<Color> currentColor = new SimpleObjectProperty<>(Color.RED);
    DoubleProperty currentSize = new SimpleDoubleProperty(50);
    ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);



    public ShapeType getCurrentShapeType() {
        return currentShapeType.get();
    }

    public ObjectProperty<ShapeType> currentShapeTypeProperty() {
        return currentShapeType;
    }

    public double getCurrentSize() {
        return currentSize.get();
    }

    public DoubleProperty currentSizeProperty() {
        return currentSize;
    }

    public ObservableList<ShapeType> getShapeTypeList() {
        return shapeTypeList;
    }

    public Color getCurrentColor() {
        return currentColor.get();
    }

    public ObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }

    public ShapeModel() {

    }
    public Deque<Shape> getShapesList() {
        return shapesList;
    }
    public void add(Shape shape) {
        shapesList.add(shape);
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
        for(Shape i: shapesList){
            i.drawCanvas(graphicsContext);

            }

        }


    }





