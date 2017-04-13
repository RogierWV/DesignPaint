package designpaint;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main entry point for the program, sets base options and creates the Canvas.
 * @see Canvas
 */
public class DesignPaint {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
        PaintFrame frame = new PaintFrame("Design Patterns Paint");
        frame.setVisible(true); // make window visible
    }
}
