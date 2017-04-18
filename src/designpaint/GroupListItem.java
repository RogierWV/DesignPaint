package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class GroupListItem {
    AtomicReference<Component> pointer;
    String printedName;

    public GroupListItem(AtomicReference<Component> pointer, String name) {
        this.pointer = pointer;
        this.printedName = name;
    }

    @Override
    public String toString() {
//        return pointer.get().getClass().getSimpleName();
        return printedName;
    }
}
