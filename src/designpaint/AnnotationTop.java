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
        g.drawString(text, sub.getX()+sub.getW()/2-text.length()*2, sub.getY()-5);
        if(!(sub instanceof Composite)) sub.draw(g);
    }
    
    @Override
    public String toString() {
        return "ornament top \"" + text + "\"";
    }
}
