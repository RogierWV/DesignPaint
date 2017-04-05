/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpaint;

import java.awt.Color;
import javax.swing.JFrame;


/**
 *
 * @author danny
 */
public class DesignPaint {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        
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