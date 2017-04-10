package designpaint;

import java.util.List;

public class Command_Select extends Command{

    public Command_Select(List<Shape> shapes) {
        super(shapes);
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
