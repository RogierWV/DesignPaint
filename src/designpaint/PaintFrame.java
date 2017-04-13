package designpaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class PaintFrame extends JFrame {

    public PaintFrame(String title) throws HeadlessException {
        super(title);
        
        Canvas panel = new Canvas();
        panel.setBackground(Color.white);
        
        // add panel to the center of window
        panel.setPreferredSize(new Dimension(800, 700));
        this.getContentPane().add(panel,BorderLayout.WEST);
        this.getContentPane().add(new GroupList(panel.getTree(), panel),BorderLayout.EAST);
        this.setJMenuBar(new PaintMenuBar(panel));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
