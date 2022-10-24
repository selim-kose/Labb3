package se.selimkose.labb3.Model;

import java.util.ArrayList;
import java.util.List;

public class ShapeStorage {

    List<Shape> shapes = new ArrayList<>();


    public void draw(){
        shapes.get(shapes.size()-1);
    }

    public void add(Shape shape){
        shapes.add(shape);
    }

}
