package designpaint;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Strategy for drawing Rectangles
 * @see Shape
 */
public class RectangleStrategy extends ShapeStrategy {
    private static RectangleStrategy instance = null;
    private RectangleStrategy() {}
    
    /**
     * Gets the instance
     * @return instance of this Strategy
     */
    public static RectangleStrategy getInstance() {
        if (instance == null) instance = new RectangleStrategy();
        return instance;
    }
    
    /**
     * Draw a Rectangle
     * @param g Graphics to draw on
     * @param s Shape to draw
     */
    @Override
    public void draw(Graphics g, Shape s) {
        g.setColor(Color.black);
        g.drawRect(s.coordinateX, s.coordinateY, Math.abs(s.width), Math.abs(s.height));
    }
    
    @Override
    public String getName() {
        return "Rectangle";
    }

    /**
     * Return string representation of Shape s
     * @param s Shape to return string representation of
     * @return String representation of s
     */
    @Override
    public String toString(Shape s) {
        return "rectangle " + s.coordinateX + " " + s.coordinateY + " " + (s.coordinateX + s.width) + " " + (s.coordinateY + s.height);
    }

}
