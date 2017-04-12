package designpaint;

import java.awt.Dimension;
import javax.swing.JList;

public class GroupList extends JList {

    public GroupList(GroupListItem[] items, Canvas panel) {
        super(items);
        this.setPreferredSize(new Dimension(200, 700));
        this.addListSelectionListener((e) -> {
            System.out.println(items[this.getSelectedIndex()].pointer.get().toString());
            panel.setSelected(items[this.getSelectedIndex()].pointer);
        });
    }
    
}
