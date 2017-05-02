package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 */
public class Decorator implements Component {

    private final String text;
    private final Component sub;

    public Decorator(String text, Component c) {
        this.text = text;
        this.sub = c;
    }
    
    @Override
    public GroupListItem toListItem(String prefix) {
        return new GroupListItem(new AtomicReference<>(this), prefix+text);
    }

    @Override
    public List<GroupListItem> toFlatList(String prefix) {
        List<GroupListItem> ret = new ArrayList<>();
        ret.add(this.toListItem(prefix));
        return ret;
    }

    @Override
    public String print(String prefix) {
//        return sub.print(prefix);
        return null;
    }

    @Override
    public void resize(int offsetW, int offsetH) {
//        sub.resize(offsetW, offsetH);
    }

    @Override
    public void move(int offsetX, int offsetY) {
//        sub.move(offsetX, offsetY);
    }

    @Override
    public Component select(int x, int y) {
        return sub.select(x, y);
    }

    @Override
    public int getSmallestArea(int x, int y) {
        return sub.getSmallestArea(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(text, sub.getX(), sub.getY());
//        sub.draw(g);
    }

    @Override
    public int getOX() {
        return sub.getOX();
    }

    @Override
    public int getOY() {
        return sub.getOY();
    }

    @Override
    public int getW() {
        return sub.getW();
    }

    @Override
    public int getH() {
        return sub.getH();
    }

    @Override
    public int getFarX() {
        return sub.getFarX();
    }

    @Override
    public int getX() {
        return sub.getX();
    }

    @Override
    public int getY() {
        return sub.getY();
    }

    @Override
    public int getFarY() {
        return sub.getFarY();
    }

    @Override
    public AtomicReference<Composite> getGroup() {
        return sub.getGroup();
    }

    @Override
    public void setGroup(AtomicReference<Composite> composite) {
        sub.setGroup(composite);
    }

    @Override
    public void Accept(Visitor v) {
        v.Visit(this);
        sub.Accept(v);
    }
    
    @Override
    public String toString() {
        return "ornament top " + text + "\r\n" + sub.toString();
    }
}
