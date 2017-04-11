package designpaint;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Command_Select extends Command{
    AtomicReference<Shape> selectedShape;
    Shape previousSelectedShape;
    int x;
    int y;

    public Command_Select(List<Shape> shapes, AtomicReference<Shape> selectedShape, int x, int y) {
        super(shapes);
        this.selectedShape = selectedShape;
        this.previousSelectedShape = selectedShape.get();
        this.x = x;
        this.y = y;
        System.out.println("Select: " + selectedShape);
    }

    @Override
    public void execute() {
        int size = shapes.size();
        int area = -1;
        for (int i = 0; i < size; i++) {
            int farX = shapes.get(i).getCoordinateX() + shapes.get(i).getWidth();
            int farY = shapes.get(i).getCoordinateY() + shapes.get(i).getHeight();
            if(shapes.get(i).getCoordinateX() < x && shapes.get(i).getCoordinateY() < y && farX > x && farY > y){
                if(area == -1){
                    area = shapes.get(i).getArea();
                    clearSelect();
                    selectedShape.set(shapes.get(i));
                }else{
                    if(area > shapes.get(i).getArea()){
                        area = shapes.get(i).getArea();
                        clearSelect();
                        selectedShape.set(shapes.get(i));
                    }
                }
            }
        }
        System.out.println("Select2: " + selectedShape);
    }

    @Override
    public void undo() {
        if(previousSelectedShape != null){
            selectedShape.set(previousSelectedShape);
        }else{
            clearSelect();
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void clearSelect(){
        int size = shapes.size();
        for (Iterator<Shape> it = shapes.iterator(); it.hasNext(); ) {
            Shape shape = it.next();
            if ("Select".equals(shape.getClass().getSimpleName())) {
                it.remove();
                
            }
        }
//        for (int i = 0; i < size; i++) {
//            if("Select".equals(shapes.get(i).getClass().getSimpleName())){
//                shapes.remove(i);
//                break;
//            }
//        }
    }
    
}
