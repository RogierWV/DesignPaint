package designpaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;

public class PaintFrame extends JFrame {

    public PaintFrame(String title) throws HeadlessException {
        super(title);
        
        Canvas panel = new Canvas();
        panel.setBackground(Color.white);
        
        // add panel to the center of window
        panel.setPreferredSize(new Dimension(800, 700));
        this.getContentPane().add(panel,BorderLayout.WEST);
        GroupList list = new GroupList(panel);
        this.getContentPane().add(list,BorderLayout.EAST);
        this.setJMenuBar(new PaintMenuBar(panel));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
