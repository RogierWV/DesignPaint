package designpaint;

import java.awt.Dimension;
import javax.swing.JList;

/**
 * JList to display groups
 */
public class GroupList extends JList {
    
    GroupListModel model;
    
    /**
     * Creates new GroupList
     * @param panel Canvas to use for the model
     */
    public GroupList(Canvas panel) {
        super();
        this.setFocusable(false);
        model = new GroupListModel(panel.getRoot());
        this.setModel(model);
        
        this.addListSelectionListener((e) -> {
            panel.setSelected(model.getItemsRef().get().get(this.getSelectedIndex()).pointer);
        });
        // Start a new thread to update the list
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
