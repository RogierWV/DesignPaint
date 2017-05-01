package designpaint;

import java.util.List;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A component on the canvas.
 */
public interface Component {

    /**
     * Creates a GroupListItem from a Component.
     * @param prefix prefix for recursiveness.
     * @return A GroupListItem for in a GroupList
     */
    public GroupListItem toListItem(String prefix);

    /**
     * Returns a flatList of GroupListItems
     * @param prefix prefix for recursiveness.
     * @return A flatList of GroupListItems.
     */
    public List<GroupListItem> toFlatList(String prefix);

    /**
     * Basically ToString with recursion.
     * @param prefix Used for recursive use.
     * @return A string.
     */
    public String print(String prefix);

    /**
     * Resizes the component.
     * @param offsetW width offset to resize to.
     * @param offsetH height offset to resize to.
     */
    public void resize(int offsetW, int offsetH);

    /**
     * Move this shape to a new location.
     * @param offsetX offset to new location on X-axis
     * @param offsetY offset to new location on Y-axis
     */
    public void move(int offsetX, int offsetY);

    /**
     * Mark this component as selected item, if it's at a location.
     * @param x X coordinate of the location.
     * @param y Y coordinate of the location.
     * @return this component.
     */
    public Component select(int x, int y);

    /**
     * Returns smallest area around a point.
     * @param x X coordinate of search area.
     * @param y Y coordinate of search area.
     * @return Smallest area.
     */
    public int getSmallestArea(int x, int y);

    /**
     * Draws a component on to the canvas.
     * @param g graphics component used for drawing.
     */
    public void draw(Graphics g);

    /**
     * Gives X coordinate of origin.
     * @return X coordinate of origin.
     */
    public int getOX();

    /**
     * Gives Y coordinate of origin.
     * @return Y coordinate of origin.
     */
    public int getOY();
    
    //Clear getters & setters
    public int getW();
    public int getH();
    public int getFarX();
    public int getX();
    public int getY();
    public int getFarY();
    public AtomicReference<Composite> getGroup();
    public void setGroup(AtomicReference<Composite> composite);
    
    /**
     * Accept function for visitor.
     * @param v Visitor to accept.
     */
    public abstract void Accept(Visitor v);
}
