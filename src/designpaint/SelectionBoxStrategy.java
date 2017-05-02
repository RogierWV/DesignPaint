package designpaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Strategy for drawing selection boxes
 * @see Shape
 */
public class SelectionBoxStrategy extends ShapeStrategy {
    private static SelectionBoxStrategy instance = null;
    private SelectionBoxStrategy() {}
    
    /**
     * Gets the instance
     * @return instance of this Strategy
     */
    public static SelectionBoxStrategy getInstance() {
        if (instance == null) instance = new SelectionBoxStrategy();
        return instance;
    }
    
    /**
     * Draw a selection box
     * @param g Graphics to draw on
     * @param s Shape to draw
     */
    @Override
    public void draw(Graphics g, Shape s) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        double thickness = 2;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke((float) thickness));
        
        g2.drawRect(s.coordinateX, s.coordinateY, Math.abs(s.width), Math.abs(s.height));
        g2.setStroke(oldStroke);
    }

    /**
     * Return string representation of Shape s
     * @param s Shape to return string representation of
     * @return String representation of s
     */
    @Override
    public String toString(Shape s) {
        return "select " + s.coordinateX + " " + s.coordinateY + " " + (s.coordinateX + s.width) + " " + (s.coordinateY + s.height);
    }

}
