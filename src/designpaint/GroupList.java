package designpaint;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JList;

public class GroupList extends JList {

    AtomicReference<Composite> tree;
    GroupListItem[] items;
    
//    public GroupList(GroupListItem[] items, Canvas panel) {
//        super(items);
//        this.setPreferredSize(new Dimension(200, 700));
//        this.addListSelectionListener((e) -> {
//            System.out.println(items[this.getSelectedIndex()].pointer.get().toString());
//            panel.setSelected(items[this.getSelectedIndex()].pointer);
//        });
//    }
    
    public GroupList(AtomicReference<Composite> tree, Canvas panel) {
        super(tree.get().toFlatList().toArray());
        this.tree = tree;
        this.update();
        this.setPreferredSize(new Dimension(200, 700));
        this.addListSelectionListener((e) -> {
            System.out.println(items[this.getSelectedIndex()].pointer.get().toString());
            panel.setSelected(items[this.getSelectedIndex()].pointer);
        });
    }
    
    public void update() {
        this.items = (GroupListItem[])tree.get().toFlatList().stream().map((c) -> c.toListItem()).toArray();
        System.out.println(Arrays.toString(this.items));
    }
}
