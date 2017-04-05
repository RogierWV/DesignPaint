package designpaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Elipse extends Shape {

    public Elipse(int id, Point coordinates, int width, int height, Graphics graphics) {
        super(id, coordinates, width, height, graphics);
        this.draw(coordinates, width, height);
    }
    
    @Override
    public void draw(Point coordinates, int width, int height) {
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
        graphics.setColor(Color.black);
        graphics.drawOval(coordinates.x, coordinates.y, width, height);
    }
    
}
