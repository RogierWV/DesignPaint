package designpaint;

import static java.awt.event.KeyEvent.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Menu bar for the application
 */
public class PaintMenuBar extends JMenuBar {
    
    /**
     * Creates a new menu bar
     * @param panel Canvas to be used as listener
     */
    public PaintMenuBar(Canvas panel) {
        super();
        this.setFocusable(false);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(VK_F);

        JMenuItem save = new JMenuItem("Save (F)");
        save.setMnemonic(VK_S);
        save.setActionCommand("save");
        save.addActionListener(panel);
        fileMenu.add(save);

        JMenuItem load = new JMenuItem("Load (L)");
        load.setMnemonic(VK_L);
        load.setActionCommand("load");
        load.addActionListener(panel);
        fileMenu.add(load);

        this.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(VK_E);

        JMenuItem select = new JMenuItem("Select (S)");
        select.setMnemonic(VK_S);
        select.setActionCommand("select");
        select.addActionListener(panel);
        editMenu.add(select);

        JMenuItem resize = new JMenuItem("Resize (Z)");
        resize.setMnemonic(VK_R);
        resize.setActionCommand("resize");
        resize.addActionListener(panel);
        editMenu.add(resize);

        JMenuItem move = new JMenuItem("Move (M)");
        move.setMnemonic(VK_M);
        move.setActionCommand("move");
        move.addActionListener(panel);
        editMenu.add(move);

        editMenu.addSeparator();

        JMenuItem undo = new JMenuItem("Undo (U)");
        undo.setMnemonic(VK_U);
        undo.setActionCommand("undo");
        undo.addActionListener(panel);
        editMenu.add(undo);

        JMenuItem redo = new JMenuItem("Redo (I)");
        redo.setMnemonic(VK_R);
        redo.setActionCommand("redo");
        redo.addActionListener(panel);
        editMenu.add(redo);

        this.add(editMenu);

        JMenu shapeMenu = new JMenu("Components");
        shapeMenu.setMnemonic(VK_C);

        JMenuItem rect = new JMenuItem("Rectangle (R)");
        rect.setMnemonic(VK_R);
        rect.setActionCommand("rectangle");
        rect.addActionListener(panel);
        shapeMenu.add(rect);

        JMenuItem ellipse = new JMenuItem("Ellipse (E)");
        ellipse.setMnemonic(VK_E);
        ellipse.setActionCommand("ellipse");
        ellipse.addActionListener(panel);
        shapeMenu.add(ellipse);
        
        JMenuItem group = new JMenuItem("New group");
        group.setMnemonic(VK_G);
        group.setActionCommand("group");
        group.addActionListener(panel);
        shapeMenu.add(group);

        this.add(shapeMenu);
    }
}
