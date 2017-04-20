package designpaint;

import java.util.List;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicReference;

public interface Component {
    public AtomicReference<Composite> getGroup();
    public void setGroup(AtomicReference<Composite> composite);
    public GroupListItem toListItem(String prefix);
    public List<GroupListItem> toFlatList(String prefix);
    public String print(String prefix);
    public void resize(int offsetW, int offsetH);
    public void move(int offsetX, int offsetY);
    public Component select(int x, int y);
    public int getSmallestArea(int x, int y);
    public void draw(Graphics g);
    public int getX();
    public int getOX();
    public int getY();
    public int getOY();
    public int getW();
    public int getH();
    public int getFarX();
    public int getFarY();
    
    public abstract void Accept(Visitor v);
}
