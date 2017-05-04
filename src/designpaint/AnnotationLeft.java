package designpaint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * 
 */
public class AnnotationLeft extends Annotation {

    public AnnotationLeft(String text, Component c) {
        super(text, c);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform t = g2.getTransform();
        g2.rotate(Math.PI/2);
        g2.drawString(text, (sub.getY()+(sub.getH()/2)-text.length()*2), -(sub.getX())+15);
        g2.setTransform(t);
        if(!(sub instanceof Composite)) sub.draw(g);
    }

    @Override
    public String toString() {
        return "ornament left \"" + text + "\"";
    }
}
