package designpaint;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Rectangle shape that can be drawn on a Canvas
 * @see Canvas
 */
public class Rectangle extends Shape {

    /**
     * Creates a Rectangle at certain coordinates, on a canvas.
     * @param id The id of the shape.
     * @param coordinateX The X coordinate of the origin of the shape.
     * @param coordinateY The Y coordinate of the origin of the shape.
     * @param width The width of the shape.
     * @param height Height of the shape.
     */
    public Rectangle(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
    }

    /**
     * Draws the shape at its coordinates.
     * @param graphics Graphics generator
     * @return this (for mapping)
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(coordinateX, coordinateY, Math.abs(width), Math.abs(height));
        //return this;
    }
    
    /**
     * Gets the name of the Shape subclass used to instantiate this.
     * @return The name of the Shape type (presumably Rectangle)
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
        return "rectangle " + coordinateX + " " + coordinateY + " " + width + " " + height;
    }
}
