package designpaint;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Command for selecting a Component
 * @see Component
 */
public class Command_Select extends Command {
    AtomicReference<Component> selectedShape;
    Composite root;
    Component previousSelectedShape;
    int x;
    int y;

    /**
     * Creates a Command_Select
     * @param root The Composite used as root
     * @param selectedShape Reference to the selected shape
     * @param x X coord of click
     * @param y Y coord of click
     */
    public Command_Select(Composite root, AtomicReference<Component> selectedShape, int x, int y) {
        this.selectedShape = selectedShape;
        this.previousSelectedShape = selectedShape.get();
        this.root = root;
        this.x = x;
        this.y = y;
    }

    /**
     * Executes the command, actually selecting
     */
    @Override
    public void execute() {
        selectedShape.set(root.select(x, y));
    }

    /**
     * Undoes command by restoring the previously selected shape
     */
    @Override
    public void undo() {
        selectedShape.set(previousSelectedShape);
    }
    
}
