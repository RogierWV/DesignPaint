package designpaint;

import java.util.Iterator;
import java.util.List;

public class Command_Move extends Command{
    int id;
    int x;
    int y;
    
    int oldX;
    int oldY;
    
    int clickX;
    int clickY;

    public Command_Move(List<Shape> shapes, int id, int x, int y, int clickX, int clickY, int oldXMove, int oldYMove) {
        super(shapes);
        this.id = id;
        this.x = x;
        this.y = y;
        this.clickX = clickX;
        this.clickY = clickY;
        
        this.oldX = oldXMove;
        this.oldY = oldYMove;
    }

    @Override
    public void execute() {
        for(Shape shape : shapes){
            if(shape.getId() == id){
                
                int originX = shape.getOriginX();
                int originY = shape.getOriginY();
                int offsetX = x - clickX;
                int offsetY = y - clickY;
                shape.setDimensions(originX + offsetX, originY + offsetY, shape.getWidth(), shape.getHeight());
                
                
                //oldX = shape.getCoordinateX();
                //oldY = shape.getCoordinateY();
                //shape.setDimensions(x, y, shape.getWidth(), shape.getHeight());
            }
        }
    }

    @Override
    public void undo() {
        for (Shape shape : shapes) {
            if (shape.getId() == id) {
                shape.setDimensions(oldX, oldY, shape.getWidth(), shape.getHeight());
                
            }
        }//        for(Shape shape : shapes){
//            int index = shapes.indexOf(shape);
//            if(shape.getId() == id){
//                shape.setDimensions(oldX, oldY, shape.getWidth(), shape.getHeight());
//            }
//        }
  }
       
}
