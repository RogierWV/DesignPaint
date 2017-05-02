package designpaint;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Command to add an Ellipse
 * @see Ellipse
 */
public class Command_AddEllipse extends Command {
    AtomicReference<Composite> group;
    AtomicReference<Component> newShape;
    Component shape;
    
    /**
     * Creates a new Command_AddEllipse
     * @param group Reference to containing group
     * @param newShape Reference to the shape to add
     * @param x X coord
     * @param y Y coord
     * @param w Width
     * @param h Height
     */
    public Command_AddEllipse(AtomicReference<Composite> group, AtomicReference<Component> newShape, int x, int y, int w, int h) {
        int width = w - x;
        int height = h - y;
        this.group = group;
        this.newShape = newShape;
        this.newShape.set(new Shape(x, y, width, height, EllipseStrategy.getInstance()));
        this.shape = newShape.get();
    }

    /**
     * Actually adds the Ellipse to the specified group
     */
    @Override
    public void execute() {
        group.get().add(shape);
    }

    /**
     * Undoes adding the Ellipse, removes it from its containing group
     */
    @Override
    public void undo() {
       group.get().remove(shape);
    }
    
}
