/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpaint;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HackSlash
 */
public class Command_AddRectangle extends Command {
    int id;
    int x;
    int y;
    int w;
    int h;

    public Command_AddRectangle(List<Shape> shapes, int id, int x, int y, int w, int h) {
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
        shapes.add(new Rectangle(id, x, y, width, height));
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
//            }
//        }
    }
    
}
