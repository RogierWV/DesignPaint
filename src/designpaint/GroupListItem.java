package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class GroupListItem {
    AtomicReference<Shape> pointer; //TODO: replace with actual class

    public GroupListItem(AtomicReference<Shape> pointer) {
        this.pointer = pointer;
    }

    @Override
    public String toString() {
        return pointer.get().getClass().getSimpleName()+" "+pointer.get().getArea();
    }
}
