package designpaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Select extends Shape {

    public Select(int id, int coordinateX, int coordinateY, int width, int height) {
        super(id, coordinateX, coordinateY, width, height);
    }

    @Override
    public Shape draw(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(Color.red);
        double thickness = 2;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke((float) thickness));
        
        g2.drawRect(coordinateX, coordinateY, width, height);
        g2.setStroke(oldStroke);
        return this;
    }
    
    public String getShapeType(){
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "select " + coordinateX + " " + coordinateY + " " + width + " " + height;
    }
}
