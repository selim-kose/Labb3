package se.selimkose.labb3.Model;

import se.selimkose.labb3.Model.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class GeometriChatModel {

  private List<Shape> shapes = new ArrayList<>();


  public GeometriChatModel(){

  }

  public List<Shape> getShapes() {
    return shapes;
  }

  public void undo(){
    shapes.remove(shapes.size()-1);
  }

  public void clearAll(){
    shapes.clear();
  }

}
