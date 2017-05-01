package designpaint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.AbstractListModel;

public class GroupListModel extends AbstractListModel {

    AtomicReference<Composite> tree;
    List<GroupListItem> items;
    
    public GroupListModel(AtomicReference<Composite> tree) {
        super();
        this.items = new ArrayList<GroupListItem>();
        this.tree = tree;
        this.update();
    }
    
    public boolean update() {
        List<GroupListItem> olditems = items;
        items.clear();
//        items.add(tree.get().toListItem());
//        tree.get().toFlatList().forEach((c) -> {
//            items.add(c.toListItem());
//        });
        items.addAll(tree.get().toFlatList(""));
        //System.out.println(this.items);
        this.fireContentsChanged(items, 0, items.size());
        return items.equals(olditems);
    }
    
    public AtomicReference<List<GroupListItem>> getItemsRef() {
        return new AtomicReference<>(items);
    }

    @Override
    public int getSize() {
        return items.size();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getElementAt(int index) {
        return items.get(index);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
