package designpaint;

import java.util.List;

public class Command_Resize extends Command{
    int id;
    int w;
    int h;
    
    int oldW;
    int oldH;

    public Command_Resize(List<Shape> shapes, int id, int w, int h) {
        super(shapes);
        this.id = id;
        this.w = w;
        this.h = h;
    }

    @Override
    public void execute() {
        for(Shape shape : shapes){
            if(shape.getId() == id){
                oldW = shape.getWidth();
                oldH = shape.getHeight();
                
                int x = shape.getOriginX();
                int y = shape.getOriginY();
                int width = w - x;
                int height = h - y;
                
                shape.setDimensions(x, y, width, height);
            }
        }
    }

    @Override
    public void undo() {
        for(Shape shape : shapes){
            if(shape.getId() == id){
                int x = shape.getOriginX();
                int y = shape.getOriginY();
                
                shape.setDimensions(x, y, oldW, oldH);
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
