package designpaint;

import java.awt.Graphics;

/**
 * 
 */
public class AnnotationTop extends Annotation {
    
    public AnnotationTop(String text, Component c) {
        super(text, c);
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawString(text, sub.getX(), sub.getY());
        if(!(sub instanceof Composite)) sub.draw(g);
    }
    
}
