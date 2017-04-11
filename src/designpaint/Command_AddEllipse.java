package designpaint;

import java.util.Iterator;
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
        int width = w - x;
        int height = h - y;
        shapes.add(new Ellipse(id, x, y, width, height));
        System.out.println(shapes);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        for (Iterator<Shape> it = shapes.iterator(); it.hasNext(); ) {
            Shape shape = it.next();
            if (shape.getId() == id) {
                it.remove();
            }
        }
//        for(Shape shape : shapes){
//            if(shape.getId() == id){
//                shapes.remove(shape);
//                System.out.println("undo");
//                System.out.println(shapes.get(id));
//                shapes.listIterator(id).remove();
//            }
//        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
