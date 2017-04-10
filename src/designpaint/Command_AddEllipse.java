package designpaint;

import java.util.List;

public class Command_AddEllipse extends Command{
    int id;
    int x;
    int y;
    int w;
    int h;

    public Command_AddEllipse(List<Shape> shapes, int id, int x, int y, int w, int h) {
        super(shapes);
        this.id = id;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void execute() {
        shapes.add(new Ellipse(id, x, y, w, h));
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        for(Shape shape : shapes){
            if(shape.getId() == id){
                shapes.remove(shape);
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
