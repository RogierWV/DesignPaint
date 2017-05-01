package designpaint;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Command to move a Component
 * @see Component
 */
public class Command_Move extends Command {
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
    /**
     * Creates the Command_Move with required coordinates
     * @param component The component to move
     * @param clickX Starting cursor X coord
     * @param clickY Starting cursor Y coord
     * @param x Cursor X coord
     * @param y Cursor Y coord
     * @param oldX Previous cursor X coord
     * @param oldY Previous cursor Y coord
     */
    public Command_Move(AtomicReference<Component> component, int clickX, int clickY, int x, int y, int oldX, int oldY) {
        this.component = component;
        this.clickX = clickX;
        this.clickY = clickY;
        this.x = x;
        this.y = y;
        this.oldX = oldX;
        this.oldY = oldY;
    }

    /**
     * Executes the command, moving the Component to a new position
     */
    @Override
    public void execute() {
        System.out.println(-(component.get().getX() - (x-clickX) - oldX));
        MoveVisitor move = new MoveVisitor(-(component.get().getX() - (x-clickX) - oldX), -(component.get().getY() - (y-clickY) - oldY));
        component.get().Accept(move);
        //component.get().move(-(component.get().getX() - (x-clickX) - oldX), -(component.get().getY() - (y-clickY) - oldY));
    }
    
    /**
     * Undoes command, moving the Component to its original position
     */
    @Override
    public void undo() {
        //component.get().move(oldX - component.get().getX(), oldY - component.get().getY());
        component.get().Accept(new MoveVisitor(oldX - component.get().getX(), oldY - component.get().getY()));
    }
       
}
