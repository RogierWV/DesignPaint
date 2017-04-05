package designpaint;

import java.awt.Graphics;

public abstract class Shape {
    protected final int id;
    protected int coordinateX;
    protected int coordinateY;
    protected int width;
    protected int height;
    
    /**
     * Creates a shape at certain coordinates, on a canvas.
     * @param id The id of the shape.
     * @param coordinates The Coordinates of the shape.
     * @param width The width of the shape.
     * @param height Height of the shape.
     * @param graphics The graphicsgenerator used to create the shape.
     */
    Shape(int id, int coordinateX, int coordinateY, int width, int height) {
        this.id = id;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Draws the shape at it's coordinates.
     * @param graphics Graphics generator
     */
    public void draw(Graphics graphics) {}
    
    /**
     * Sets the dimmentions of the shape.
     * @param coordinateX Location of the shape on the X-axis
     * @param coordinateY Location of the shape on the Y-axis
     * @param width The width of the shape.
     * @param height Height of the shape.
     */
    public void setDimensions(int coordinateX, int coordinateY, int width, int height) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


   }
