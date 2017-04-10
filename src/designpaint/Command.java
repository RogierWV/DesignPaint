package designpaint;

import java.util.List;

public abstract class Command {
    
    List<Shape> shapes;
    
    Command(List<Shape> shapes){
        this.shapes = shapes;
    }
    
    public abstract void execute();
    public abstract void undo();
}
