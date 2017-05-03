package designpaint;

import java.awt.Graphics;

/**
 * Base abstract class for strategies to be used by shapes
 * @see Shape
 */
public abstract class ShapeStrategy {
    /**
     * Get the instance of this strategy (to be overridden by implementations)
     * @return null
     */
    public static ShapeStrategy getInstance() {return null;}
    
    /**
     * Draw a shape on given graphics
     * @param g Graphics to draw on
     * @param s Shape to draw
     */
    public abstract void draw(Graphics g, Shape s);
    
    /**
     * Gets the name to be used for menus
     * @return Name
     */
    public abstract String getName();
    
    /**
     * Return string representation of Shape s
     * @param s Shape to return string representation of
     * @return String representation of s
     */
    public abstract String toString(Shape s);
}
