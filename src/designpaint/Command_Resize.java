package designpaint;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Command to resize a Component
 * @see Component
 * @see ResizeVisitor
 */
public class Command_Resize extends Command {
    AtomicReference<Component> component;
    int currentOffsetW;
    int currentOffsetH;
    int nextOffsetW;
    int nextOffsetH;
    
    /**
     * Creates the Command_Resize with required coordinates
     * @param component The component to resize
     * @param clickX Starting cursor X coord
     * @param clickY Starting cursor Y coord
     * @param x Cursor X coord
     * @param y Cursor Y coord
     * @param oldW Previous width
     * @param oldH Previous height
     */
    public Command_Resize(AtomicReference<Component> component, int clickX, int clickY, int x, int y, int oldW, int oldH) {
        this.component = component;
        currentOffsetW = component.get().getOX() + component.get().getW() - oldW;
        currentOffsetH = component.get().getOY() + component.get().getH() - oldH;
        nextOffsetW = x - clickX;
        nextOffsetH = y - clickY;
    }

    /**
     * Execute the command, resizing the Component
     */
    @Override
    public void execute() {
        ResizeVisitor resize = new ResizeVisitor(nextOffsetW - currentOffsetW, nextOffsetH - currentOffsetH);
        component.get().Accept(resize);
    }

    /**
     * Undoes command, setting the Component's size back to its original value
     */
    @Override
    public void undo() {
        currentOffsetW = 0;
        currentOffsetH = 0;
        component.get().Accept(new ResizeVisitor(-nextOffsetW, -nextOffsetH));
    }
    
}
