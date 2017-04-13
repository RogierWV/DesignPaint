package designpaint;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JList;

public class GroupList extends JList {

    AtomicReference<Composite> tree;
    List<GroupListItem> items;
    
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
        this.items = new ArrayList<GroupListItem>();
        this.tree = tree;
        this.update();
        this.setPreferredSize(new Dimension(200, 700));
        this.addListSelectionListener((e) -> {
            System.out.println(items.get(this.getSelectedIndex()).pointer.get().toString());
            panel.setSelected(items.get(this.getSelectedIndex()).pointer);
        });
    }
    
    public void update() {
//        this.items = (GroupListItem[])tree.get().toFlatList().stream().map((c) -> c.toListItem()).toArray();
        items.clear();
//        List<GroupListItem> ret = new ArrayList<>();
        for(Component c : tree.get().toFlatList()) {
            items.add(c.toListItem());
        }
//        this.items = ret.toArray();
        System.out.println(this.items);
    }
}
