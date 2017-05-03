package designpaint;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Strategy for drawing Ellipses
 * @see Shape
 */
public class EllipseStrategy extends ShapeStrategy {
    private static EllipseStrategy instance = null;
    private EllipseStrategy() {}
    
    /**
     * Gets the instance
     * @return instance of this Strategy
     */
    public static EllipseStrategy getInstance() {
        if (instance == null) instance = new EllipseStrategy();
        return instance;
    }
    
    /**
     * Draw an Ellipse
     * @param g Graphics to draw on
     * @param s Shape to draw
     */
    @Override
    public void draw(Graphics g, Shape s) {
        g.setColor(Color.black);
        g.drawOval(s.coordinateX, s.coordinateY, Math.abs(s.width), Math.abs(s.height));
    }

    /**
     * Return string representation of Shape s
     * @param s Shape to return string representation of
     * @return String representation of s
     */
    @Override
    public String toString(Shape s) {
        return "ellipse " + s.coordinateX + " " + s.coordinateY + " " + (s.coordinateX + s.width) + " " + (s.coordinateY + s.height);
    }

    @Override
    public String getName() {
        return "Ellipse";
    }
}
