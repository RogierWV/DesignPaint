package designpaint;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Ellipse shape that can be drawn on a Canvas
 * @see Canvas
 */
public class Ellipse extends Shape {

    /**
     * Creates an Ellipse at certain coordinates, on a canvas.
     * @param coordinateX The X coordinate of the origin of the shape.
     * @param coordinateY The Y coordinate of the origin of the shape.
     * @param width The width of the shape.
     * @param height Height of the shape.
     */
    public Ellipse(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
    }

    /**
     * Draws the shape at its coordinates.
     * @param graphics Graphics generator
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawOval(coordinateX, coordinateY, Math.abs(width), Math.abs(height));
    }
    
    /**
     * Gets the name of the Shape subclass used to instantiate this.
     * @return The name of the Shape type (presumably Ellipse)
     */
    @Override
    public String getShapeType(){
        return this.getClass().getSimpleName();
    }
    
    /**
     * Generates a String representation of this Shape, that can be saved to a file.
     * @return String representation of this Shape
     */
    @Override
    public String toString() {
        return "ellipse " + coordinateX + " " + coordinateY + " " + (coordinateX + width) + " " + (coordinateY + height);
    }
}
