package designpaint;

import java.util.List;

public class Command_Move extends Command{
    int id;
    int x;
    int y;
    
    int oldX;
    int oldY;

    public Command_Move(List<Shape> shapes, int id, int x, int y) {
        super(shapes);
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        for(Shape shape : shapes){
            if(shape.getId() == id){
                oldX = shape.getCoordinateX();
                oldY = shape.getCoordinateY();
                shape.setDimensions(x, y, shape.getWidth(), shape.getHeight());
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        for(Shape shape : shapes){
            int index = shapes.indexOf(shape);
            if(shape.getId() == id){
                shape.setDimensions(oldX, oldY, shape.getWidth(), shape.getHeight());
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
