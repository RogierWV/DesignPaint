package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class Command_Select extends Command{
    AtomicReference<Component> selectedShape;
    Composite root;
    Component previousSelectedShape;
    int x;
    int y;

    public Command_Select(Composite root, AtomicReference<Component> selectedShape, int x, int y) {
        this.selectedShape = selectedShape;
        this.previousSelectedShape = selectedShape.get();
        this.root = root;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        selectedShape.set(root.select(x, y));
        System.out.println("Select: " + selectedShape);
    }

    @Override
    public void undo() {
        selectedShape.set(previousSelectedShape);
    }
    
}
