package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;

public interface Component {
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
