package designpaint;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Command {
    
    
//    Command(List<Shape> shapes){
//        this.shapes = shapes;
//    }
    Command(){}
    
    public abstract void execute();
    public abstract void undo();
}
