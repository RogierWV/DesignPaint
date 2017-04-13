package designpaint;

import java.util.List;
import java.awt.Graphics;

public interface Component {
    public GroupListItem toListItem();
    public List<Component> toFlatList();
    public void print(String prefix);
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
}
