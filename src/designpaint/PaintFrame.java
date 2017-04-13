package designpaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JFrame;

public class PaintFrame extends JFrame {

    public PaintFrame(String title) throws HeadlessException {
        super(title);
        
        Canvas panel = new Canvas();
        panel.setBackground(Color.white);
        
        Shape a = new Rectangle( 100, 321, 50, 200);
        Shape b = new Ellipse( 0, 0, 0, 0);
        Shape c = new Rectangle( 0, 0, 0, 0);
        GroupListItem[] testItems = {new GroupListItem(new AtomicReference<>(a)),new GroupListItem(new AtomicReference<>(b)),new GroupListItem(new AtomicReference<>(c))};

        // add panel to the center of window
        panel.setPreferredSize(new Dimension(800, 700));
        this.getContentPane().add(panel,BorderLayout.WEST);
        this.getContentPane().add(new GroupList(testItems, panel),BorderLayout.EAST);
        this.setJMenuBar(new PaintMenuBar(panel));
//        if(System.getProperty("os.name").toLowerCase().contains("win"))
//            this.setSize(1017, 700); // << not working!!!
//        else
//            this.setSize(1001, 700);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
