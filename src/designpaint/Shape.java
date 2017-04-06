package designpaint;

import java.awt.Graphics;

public abstract class Shape {
    protected final int id;
    protected int coordinateX;
    protected int coordinateY;
    protected int width;
    protected int height;
    protected int originX;
    protected int originY;
    
    /**
     * Creates a shape at certain coordinates, on a canvas.
     * @param id The id of the shape.
     * @param coordinates The Coordinates of the shape.
     * @param width The width of the shape.
     * @param height Height of the shape.
     * @param graphics The graphics generator used to create the shape.
     */
    Shape(int id, int originX, int originY, int width, int height) {
        
        this.id = id;
        this.originX = originX;
        this.originY = originY;
        this.coordinateX = originX;
        this.coordinateY = originY;
        this.width = width;
        this.height = height;
        prepCoordinates(originX, originY, width, height);
    }
    
    /**
     * Draws the shape at it's coordinates.
     * @param graphics Graphics generator
     */
    public Shape draw(Graphics graphics) {
        return this;
    }
    
    /**
     * Sets the dimensions of the shape.
     * @param coordinateX Location of the shape on the X-axis
     * @param coordinateY Location of the shape on the Y-axis
     * @param width The width of the shape.
     * @param height The height of the shape.
     */
    public void setDimensions(int coordinateX, int coordinateY, int width, int height) {
//        this.coordinateX = coordinateX;
//        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
        prepCoordinates(originX, originY, width, height);
    }
    
    private void prepCoordinates(int x, int y, int w, int h){    
        if(w < 0){
            //X
            coordinateX = x + w;
            //W
            width = Math.abs(w);
        }
        if(h < 0){
            //Y
            coordinateY = y + h;
            //H
            height = Math.abs(h);
        }
    }
    
    public int[] getMidPoint(){
        int[] mid = new int[2];
        mid[0] = coordinateX + coordinateX + width;
        mid[1] = coordinateY + coordinateY + height;
        return mid;
    }
    
    public String getShapeType(){
        return this.getClass().getSimpleName();
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
    
    public int getOriginX(){
        return originX;
    }
    
    public int getOriginY(){
        return originY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "generic " + coordinateX + " " + coordinateY + " " + width + " " + height;
    }
    
   }
