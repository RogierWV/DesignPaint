package designpaint;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

    public Rectangle(int id, int coordinateX, int coordinateY, int width, int height) {
        super(id, coordinateX, coordinateY, width, height);
    }

    @Override
    public Shape draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(coordinateX, coordinateY, width, height);
        return this;
    }
    
    public String getShapeType(){
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "rectangle " + coordinateX + " " + coordinateY + " " + width + " " + height;
    }
}
