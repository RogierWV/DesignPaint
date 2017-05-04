package designpaint;

import java.awt.Graphics;

/**
 * 
 */
public class AnnotationBottom extends Annotation {
    
    public AnnotationBottom(String text, Component c) {
        super(text, c);
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(text, sub.getX()+sub.getW()/2-text.length()*2, sub.getY()+sub.getH()+15);
        if(!(sub instanceof Composite)) sub.draw(g);
    }

    @Override
    public String toString() {
        return "ornament bottom \"" + text + "\"";
    }
}
