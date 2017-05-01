package designpaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Main window for the application
 */
public class PaintFrame extends JFrame {

    /**
     * Creates a new window with a given title
     * @param title Title for the window
     * @throws HeadlessException Thrown when the JRE doesn't support graphics
     */
    public PaintFrame(String title) throws HeadlessException {
        super(title);
        
        Canvas panel = new Canvas();
        panel.setBackground(Color.white);
        
        // add panel to the center of window
        panel.setPreferredSize(new Dimension(800, 700));
        this.getContentPane().add(panel,BorderLayout.WEST);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(new GroupList(panel));
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(new Dimension(200, 700));
        this.getContentPane().add(pane,BorderLayout.EAST);
        this.setJMenuBar(new PaintMenuBar(panel));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
