package designpaint;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Display item for the GroupList class, represents a Component
 * @see GroupList
 * @see Component
 */
public class GroupListItem {
    AtomicReference<Component> pointer;
    String printedName;

    /**
     * New item
     * @param pointer Ref to the component this item represents
     * @param name Name to print (passed here to allow recursive naming)
     */
    public GroupListItem(AtomicReference<Component> pointer, String name) {
        this.pointer = pointer;
        this.printedName = name;
    }

    /**
     * Returns the name of this item
     * @return name given during creation
     */
    @Override
    public String toString() {
//        return pointer.get().getClass().getSimpleName();
        return printedName;
    }
}
