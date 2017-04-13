package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class Command_Move extends Command{
    AtomicReference<Component> component;
    int x;
    int y;
    
    int oldX;
    int oldY;
    
    int clickX;
    int clickY;
    
    //x,y are current cursor coordinates
    //clickX, clickY are original clicking coordinates
    //oldXMove, oldYmove are
    public Command_Move(AtomicReference<Component> component, int x, int y) {
        this.component = component;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        component.get().move(x, y);
    }

    @Override
    public void undo() {
        component.get().move(-x, -y);
    }
       
}
