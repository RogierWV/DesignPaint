package designpaint;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Main entry point for the program, sets base options and creates the Canvas.
 * @see Canvas
 */
public class DesignPaint {
    
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true"); //disable on bad performance
        
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