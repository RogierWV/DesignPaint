package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Base class for all shapes that can be drawn.
 * @see Canvas
 */
public class Shape implements Component{
    //protected final int id;
    protected int coordinateX;
    protected int coordinateY;
    protected int width;
    protected int height;
    protected int originX;
    protected int originY;
    
    /**
     * Creates a shape at certain coordinates, on a canvas.
     * @param id The id of the shape.
     * @param originX The X coordinate of the origin of the shape.
     * @param originY The Y coordinate of the origin of the shape.
     * @param width The width of the shape.
     * @param height Height of the shape.
     */
    Shape(int originX, int originY, int width, int height) {
        
//        this.id = id;
        this.originX = originX;
        this.originY = originY;
        this.coordinateX = originX;
        this.coordinateY = originY;
        this.width = width;
        this.height = height;
        prepCoordinates(originX, originY, width, height);
    }
    
    /**
     * Copy constructor for Shape.
     * @param shape Shape to be copied
     */
    public Shape(Shape shape) {
        this(shape.getOriginX(), shape.getOriginY(), shape.getWidth(), shape.getHeight());
        //no defensive copies are created here, since 
        //there are no mutable object fields (String is immutable)
    }
    
    /**
     * Draws the shape at its coordinates.
     * @param g
     * @return this (for mapping)
     */
    @Override
    public void draw(Graphics g) {
        
    }
    
    @Override
    public void move(int offsetX, int offsetY){
        setDimensions(originX + offsetX, originY + offsetY, width, height);
    }
    
    
    @Override
    public void resize(int offsetW, int offsetH){
        int w = width + offsetW;
        int h = height + offsetH;
        prepCoordinates(originX, originY, w, h);
    }
    
    /**
     * Sets the dimensions of the shape.
     * @param x Location of the shape on the X-axis
     * @param y Location of the shape on the Y-axis
     * @param width The width of the shape.
     * @param height The height of the shape.
     */
    private void setDimensions(int x, int y, int width, int height) {
        this.originX = x;
        this.originY = y;
//        this.width = width;
//        this.height = height;
        prepCoordinates(x, y, width, height);
    }
    
    private void prepCoordinates(int x, int y, int w, int h){    
        if(w <= 0){
            coordinateX = x + w;
        }else{
            coordinateX = x;
        }
        if(h <= 0){
            coordinateY = y + h;
        }else{
            coordinateY = y;
        }
        width = w;//Math.abs(w);
        height = h;//Math.abs(h);
    }
    
    /**
     * Calculates the Shape's area
     * @return The area of the Shape
     */
    public int getArea(){
        return height * width;
    }
    
    /**
     * Calculates the midpoint.
     * @return Midpoint (X coord on index 0, Y coord on index 1)
     */
    public int[] getMidPoint(){
        int[] mid = new int[2];
        mid[0] = coordinateX + width/2;
        mid[1] = coordinateY + height/2;
        return mid;
    }
    
    /**
     * Gets the name of the Shape subclass used to instantiate this.
     * @return The name of the Shape type
     */
    public String getShapeType(){
        return this.getClass().getSimpleName();
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and setters">
//    public int getId() {
//        return id;
//    }

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
    // </editor-fold>

    /**
     * Generates a String representation of this Shape, that can be saved to a file.
     * @return String representation of this Shape
     */
    @Override
    public String toString() {
        return "generic " + coordinateX + " " + coordinateY + " " + width + " " + height;
    }

    @Override
    public GroupListItem toListItem() {
        return new GroupListItem(new AtomicReference<>(this));
    }

    @Override
    public List<Component> toFlatList() {
        List<Component> ret = new ArrayList<>();
        ret.add(this);
        return ret;
    }
    
    @Override
    public void print(String prefix) {
        System.out.println(prefix + toString());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    @Override
    public Component select(int x, int y) {
        
        int farX = coordinateX + width;
        int farY = coordinateY + height;
        if(getCoordinateX() < x && getCoordinateY() < y && farX > x && farY > y){
            return this;
        }
        return null;
    }
    
    @Override
    public int getSmallestArea(int x, int y){
        int farX = coordinateX + width;
        int farY = coordinateY + height;
        if(getCoordinateX() < x && getCoordinateY() < y && farX > x && farY > y){
            return getArea();
        }
        return -1;
    }

    @Override
    public int getX() {
        return coordinateX;
    }
    
    @Override
    public int getOX() {
        return originX;
    }

    @Override
    public int getY() {
        return coordinateY;
    }
    
    @Override
    public int getOY() {
        return originY;
    }

    @Override
    public int getW() {
        return width;
    }

    @Override
    public int getH() {
        return height;
    }
   }
