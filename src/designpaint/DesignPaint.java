package designpaint;

import java.awt.Color;
import javax.swing.JFrame;

public class DesignPaint {
    
    public static void main(String[] args) {
        //System.setProperty("sun.java2d.opengl", "true");
        
        JFrame frame = new JFrame("Design Patterns Paint");

        Canvas panel = new Canvas();
        panel.setBackground(Color.white);

        // add panel to the center of window
        frame.getContentPane().add("Center", panel);
        frame.setSize(900, 700); // << not working!!!
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // make window visible
    }
}