package designpaint;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
//import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main entry point for the program, sets base options and creates the Canvas.
 * @see Canvas
 */
public class DesignPaint {
    
    public static void main(String[] args) {
        //System.setProperty("sun.java2d.opengl", "true"); //disable on bad performance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
        JFrame frame = new JFrame("Design Patterns Paint");

        Canvas panel = new Canvas();
        panel.setBackground(Color.white);
        
        JMenuBar mbar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem save = new JMenuItem("Save (F)");
        save.setMnemonic(KeyEvent.VK_S);
        save.setActionCommand("save");
        save.addActionListener(panel);
        fileMenu.add(save);
        
        JMenuItem load = new JMenuItem("Load (L)");
        load.setMnemonic(KeyEvent.VK_L);
        load.setActionCommand("load");
        load.addActionListener(panel);
        fileMenu.add(load);
        
        mbar.add(fileMenu);
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        
        JMenuItem select = new JMenuItem("Select (S)");
        select.setMnemonic(KeyEvent.VK_S);
        select.setActionCommand("select");
        select.addActionListener(panel);
        editMenu.add(select);
        
        JMenuItem resize = new JMenuItem("Resize (Z)");
        resize.setMnemonic(KeyEvent.VK_R);
        resize.setActionCommand("resize");
        resize.addActionListener(panel);
        editMenu.add(resize);
        
        JMenuItem move = new JMenuItem("Move (M)");
        move.setMnemonic(KeyEvent.VK_M);
        move.setActionCommand("move");
        move.addActionListener(panel);
        editMenu.add(move);
        
        editMenu.addSeparator();
        
        JMenuItem undo = new JMenuItem("Undo (U)");
        undo.setMnemonic(KeyEvent.VK_U);
        undo.setActionCommand("undo");
        undo.addActionListener(panel);
        editMenu.add(undo);
        
        JMenuItem redo = new JMenuItem("Redo (I)");
        redo.setMnemonic(KeyEvent.VK_R);
        redo.setActionCommand("redo");
        redo.addActionListener(panel);
        editMenu.add(redo);
        
        mbar.add(editMenu);
        
        JMenu shapeMenu = new JMenu("Shapes");
        shapeMenu.setMnemonic(KeyEvent.VK_S);
        
        JMenuItem rect = new JMenuItem("Rectangle (R)");
        rect.setMnemonic(KeyEvent.VK_R);
        rect.setActionCommand("rectangle");
        rect.addActionListener(panel);
        shapeMenu.add(rect);
        
        JMenuItem ellipse = new JMenuItem("Ellipse (E)");
        ellipse.setMnemonic(KeyEvent.VK_E);
        ellipse.setActionCommand("ellipse");
        ellipse.addActionListener(panel);
        shapeMenu.add(ellipse);
        
        mbar.add(shapeMenu);
        
//        JList t = new JList();

        // add panel to the center of window
        frame.getContentPane().add("Center", panel);
//        frame.getContentPane().add(t);
        frame.setJMenuBar(mbar);
        frame.setSize(900, 700); // << not working!!!
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // make window visible
    }
}