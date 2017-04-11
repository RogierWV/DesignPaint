package designpaint;

import java.util.List;

public class Command_Resize extends Command{
    int id;
    int w;
    int h;
    int anchorX;
    int anchorY;
    
    int oldW;
    int oldH;

    public Command_Resize(List<Shape> shapes, int id, int w, int h, int anchorX, int anchorY) {
        super(shapes);
        this.id = id;
        this.w = w;
        this.h = h;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        //System.out.println("Resize Constructor: "+ id);
    }

    @Override
    public void execute() {
        for(Shape shape : shapes){
            if(shape.getId() == id){
                oldW = anchorX - shape.getCoordinateX();
                oldH = anchorY - shape.getCoordinateY();
                
                int x = shape.getOriginX();
                int y = shape.getOriginY();
                int width = w - x;
                int height = h - y;
                
                shape.setDimensions(x, y, width, height);
                //System.out.println("Resize: " + id + " "+ w + " " + h);
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
    }
    
}
