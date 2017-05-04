package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 */
public class Annotation implements Component {

    protected final String text;
    protected final Component sub;

    public Annotation(String text, Component c) {
        this.text = text;
        this.sub = c;
    }
    
    @Override
    public GroupListItem toListItem(String prefix) {
        GroupListItem item = sub.toListItem(prefix);
        item.setPointer(new AtomicReference<>(this));
        return item;
    }

    @Override
    public List<GroupListItem> toFlatList(String prefix) {
        List<GroupListItem> ret = new ArrayList<>();
        ret.add(this.toListItem(prefix));
        return ret;
    }

    @Override
    public String print(String prefix) {
        return null;
    }

    @Override
    public void resize(int offsetW, int offsetH) {}

    @Override
    public void move(int offsetX, int offsetY) {}

    @Override
    public Component select(int x, int y) {
        if(sub.select(x, y) == null) return null;
        else return this;
    }

    @Override
    public int getSmallestArea(int x, int y) {
        return sub.getSmallestArea(x, y);
    }

    @Override
    public void draw(Graphics g) {
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
        if(!(sub instanceof Composite)) sub.Accept(v);
    }
    
    @Override
    public String toString() {
        return "ornament top \"" + text + "\"";
    }
}
