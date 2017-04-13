package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class GroupListItem {
    AtomicReference<Component> pointer;

    public GroupListItem(AtomicReference<Component> pointer) {
        this.pointer = pointer;
    }

    @Override
    public String toString() {
        return pointer.get().getClass().getSimpleName();
    }
}
