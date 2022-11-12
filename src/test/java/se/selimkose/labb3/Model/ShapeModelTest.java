package se.selimkose.labb3.Model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.selimkose.labb3.Model.Shape.Circle;
import se.selimkose.labb3.Model.Shape.Shape;
import se.selimkose.labb3.Model.Shape.ShapeType;

import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {

    ShapeModel shapeModel = new ShapeModel();
    Circle circle = new Circle(new Position(1,1), Color.RED,50.0);

    @Test
    void addShapeToDequeAndChecksIfSizeIsCorrect() {
        shapeModel.shapesList.add(circle);
        assertEquals(1, shapeModel.shapesList.size());
    }

    @Test
    void shapesListIsEmptyOnStart(){
        assertTrue(shapeModel.shapesList.isEmpty());
    }



}