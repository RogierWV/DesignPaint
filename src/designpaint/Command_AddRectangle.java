package designpaint;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Command to add an Rectangle
 * @see Rectangle
 */
public class Command_AddRectangle extends Command {
    AtomicReference<Composite> group;
    AtomicReference<Component> newShape;
    Component shape;

    /**
     * Creates a new Command_AddRectangle
     * @param group Reference to containing group
     * @param newShape Reference to the shape to add
     * @param x X coord
     * @param y Y coord
     * @param w Width
     * @param h Height
     */
    public Command_AddRectangle(AtomicReference<Composite> group, AtomicReference<Component> newShape, int x, int y, int w, int h) {
        int width = w - x;
        int height = h - y;
        this.group = group;
        this.newShape = newShape;
        this.newShape.set(new Shape(x, y, width, height, RectangleStrategy.getInstance()));
        this.shape = newShape.get();
    }

    /**
     * Actually adds the Rectangle to the specified group
     */
    @Override
    public void execute() {
        group.get().add(shape);
    }

    /**
     * Undoes adding the Rectangle, removes it from its containing group
     */
    @Override
    public void undo() {
       group.get().remove(shape);
    }
    
}
