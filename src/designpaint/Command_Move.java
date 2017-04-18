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
    public Command_Move(AtomicReference<Component> component, int clickX, int clickY, int x, int y, int oldX, int oldY) {
        this.component = component;
        this.clickX = clickX;
        this.clickY = clickY;
        this.x = x;
        this.y = y;
        this.oldX = oldX;
        this.oldY = oldY;
    }

    @Override
    public void execute() {
        //component.get().move(-(component.get().getX() - (x-clickX) - oldX), -(component.get().getY() - (y-clickY) - oldY));
        MoveVisitor move = new MoveVisitor(-(component.get().getX() - (x-clickX) - oldX), -(component.get().getY() - (y-clickY) - oldY));
        component.get().Accept(move);
    }

    @Override
    public void undo() {
        component.get().move(oldX - component.get().getX(), oldY - component.get().getY());
    }
       
}
