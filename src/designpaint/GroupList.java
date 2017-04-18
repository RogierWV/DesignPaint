package designpaint;

import java.awt.Dimension;
import javax.swing.JList;

/**
 *
 * @author snyx
 */
public class GroupList extends JList {
    
    GroupListModel model;

    public GroupList(Canvas panel) {
        super();
        model = new GroupListModel(panel.getTree(), panel);
        this.setModel(model);
        this.setPreferredSize(new Dimension(200, 700));
        this.addListSelectionListener((e) -> {
            System.out.println(model.getItemsRef().get().get(this.getSelectedIndex()).pointer.get().toString());
            panel.setSelected(model.getItemsRef().get().get(this.getSelectedIndex()).pointer);
        });
        new Thread(() -> {
            while(true){
                if(model.update()) panel.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }
    
}
