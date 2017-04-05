package designpaint;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Shape {

    public Ellipse(int id, int coordinateX, int coordinateY, int width, int height, Graphics graphics) {
        super(id, coordinateX, coordinateY, width, height, graphics);
    }
 
    @Override
    public void draw() {
        graphics.setColor(Color.black);
        graphics.drawOval(coordinateX, coordinateY, width, height);
    }
    
}
