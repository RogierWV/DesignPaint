package designpaint;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
    protected final int id;
    protected Point coordinates;
    protected int width;
    protected int height;
    protected Graphics graphics;
    
    /**
     * Creates a shape at certain coordinates, on a canvas.
     * @param id The id of the shape.
     * @param coordinates The Coordinates of the shape.
     * @param width The width of the shape.
     * @param height Height of the shape.
     * @param graphics The graphicsgenerator used to create the shape.
     */
    Shape(int id, Point coordinates, int width, int height, Graphics graphics) {
        this.id = id;
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
        this.graphics = graphics;
    }
    
    /**
     * Draws the shape at it's coordinates.
     * @param coordinates Location of the shape.
     * @param width Witdh of the shape.
     * @param height Height of the shape.
     */
    public void draw(Point coordinates, int width, int height) {}

    public int getId() {
        return id;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Graphics getGraphics() {
        return graphics;
    }
}
