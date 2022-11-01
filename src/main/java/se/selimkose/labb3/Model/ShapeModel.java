package se.selimkose.labb3.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import se.selimkose.labb3.Model.Shape.Circle;
import se.selimkose.labb3.Model.Shape.Rectangle;
import se.selimkose.labb3.Model.Shape.Shape;

public class ShapeModel implements UIAction {
   // List<Shape> shapes = new ArrayList<>();
   ObservableList<Shape> observableShapeList = FXCollections.observableArrayList();


    public ShapeModel(){

    }


  /*  public void draw(){
        shapes.get(shapes.size()-1);
    }

    public void add(Shape shape){shapes.add(shape);}*/

    public void add(Shape shape) {
        observableShapeList.add(shape);
    }
    public void removeLast(){
       observableShapeList.remove(observableShapeList.size()-1);
    }

    public ObservableList<Shape> getObservableShapeList() {
        return observableShapeList;
    }

    @Override
    public void draw() {


    }

    public void undo(){

        if(observableShapeList.isEmpty()){
            return;
        }
        observableShapeList.remove(observableShapeList.size()-1);
    }

    public void setObservableShapeList(ObservableList<Shape> observableShapeList) {
        this.observableShapeList = observableShapeList;
    }

    public void renderShapes(GraphicsContext graphicsContext) {
        for (Shape i : observableShapeList) {
            graphicsContext.setFill(i.getColor());
            if (i instanceof Circle) {
                graphicsContext.fillOval(i.getPosition().x() - (i.getSize() / 2), i.getPosition().y() - (i.getSize() / 2), i.getSize(), i.getSize());
            } else if (i instanceof Rectangle) {
                graphicsContext.fillRect(i.getPosition().x() - (i.getSize() / 2), i.getPosition().y() - (i.getSize() / 2), i.getSize(), i.getSize());
            }
        }

    }

}
