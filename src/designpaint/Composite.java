package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Item that contains components (group).
 */
public class Composite implements Component{
    private final ArrayList<Component> components;
    private AtomicReference<Composite> parent;

    /**
     * Default constructor.
     */
    public Composite() {
        this.components = new ArrayList<>();
    }
    
    /**
     * Add component to composite.
     * @param component Component to add to the composite.
     */
    public void add(Component component)
    {
        if(!(component instanceof Composite)) component.setGroup(new AtomicReference<>(this));
        components.add(component);
    }
    
    /**
     * Recursive ToString method.
     * @param prefix prefix for recursiveness.
     * @return string representation of the object.
     */
    @Override
    public String print(String prefix){
        String result = prefix + toString();
        if(prefix.equals(""))
            prefix = "    ";
        else
            prefix = prefix.concat("    ");
        
        for(Component component : components)
            result = result + "\r\n" + component.print(prefix);
        return result;
    }
    
    /**
     * Returns a string representation of the object.
     * @return string representation of object.
     */
    @Override
    public String toString(){
        return "group " + components.size();
    }
   
    /**
     * Resizes the composite.
     * @param offsetW width offset to resize by.
     * @param offsetH height offset to resize by.
     */
    @Override
    public void resize(int offsetW, int offsetH) {
//        components.forEach((component) -> component.resize(offsetW, offsetH));
    }

    /**
     * Moves the composite.
     * @param offsetX X offset to move to.
     * @param offsetY Y offset to move to
     */
    @Override
    public void move(int offsetX, int offsetY) {
//        System.out.println(offsetX);
//        components.forEach((component) -> component.move(offsetX, offsetY));
    }
    
    /**
     * Remove component from composite.
     * @param component 
     */
    public void remove(Component component){
        components.remove(component);
    }
    
    /**
     * Selects a component that is within this composite, and closest to the cursor.
     * @param x cursor location.
     * @param y cursor location.
     * @return Selected Component.
     */
    @Override
    public Component select(int x, int y){
        Component selected = null;
        for(Component component : components)
            if(selected == null)
                selected = component.select(x, y);
            else if(component.getSmallestArea(x, y) < selected.getSmallestArea(x, y) && component.getSmallestArea(x, y) != -1)
                selected = component.select(x, y);
        return selected;
    }

    /**
     * Returns the area of a component that is within this composite, and closest to the cursor.
     * @param x cursor location.
     * @param y cursor location.
     * @return the area of a component that is within this composite, and closest to the cursor.
     */
    @Override
    public int getSmallestArea(int x, int y) {
        int smallestArea = -1;
        for(Component component : components)
            if(smallestArea == -1)
                smallestArea = component.getSmallestArea(x, y);
            else if(component.getSmallestArea(x, y) < smallestArea)
                smallestArea = component.getSmallestArea(x, y);
        return smallestArea;
    }

    /**
     * Draws the composite's components on the canvas.
     * @param g 
     */
    @Override
    public void draw(Graphics g) {
        components.parallelStream().forEach((component) -> component.draw(g));
    }
    
    /**
     * Returns the components in this composite as a FlatList.
     * @param prefix prefix for redundancy.
     * @return a FlatList of GroupListItems
     */
    @Override
    public List<GroupListItem> toFlatList(String prefix) {
        List<GroupListItem> ret = new ArrayList<>();
        ret.add(this.toListItem(prefix));
        components.parallelStream().forEachOrdered((Component c) -> ret.addAll(c.toFlatList(prefix+"--")));
//        ret.addAll(components.parallelStream().map((Component c) -> {
//            return c.toFlatList(prefix+"--");
//        }).flatMap((List<GroupListItem> li) -> {
//            return li.stream();
//        }).collect(Collectors.toList()));
        return ret;
    }

    /**
     * Returns the components in this composite as a List.
     * @param prefix prefix for redundancy.
     * @return a List of GroupListItems
     */
    @Override
    public GroupListItem toListItem(String prefix) {
        return new GroupListItem(new AtomicReference<>(this), prefix+"Group");
    }

    //standard getters & settters
    @Override
    public int getX() {
        int x = -1;
        for(Component component : components){
            if(x == -1){
                x = component.getX();
            }else if(component.getX() < x){
                x = component.getX();
            }
        }
        return x;
    }
    
    @Override
    public int getOX() {
        int originalX = -1;
        for(Component component : components){
            if(originalX == -1){
                originalX = component.getOX();
            }else if(component.getOX() < originalX){
                originalX = component.getOX();
            }
        }
        return originalX;
    }

    @Override
    public int getY() {
        int y = -1;
        for(Component component : components){
            if(y == -1){
                y = component.getY();
            }else if(component.getY() < y){
                y = component.getY();
            }
        }
        return y;
    }

    @Override
    public int getOY() {
        int originalY = -1;
        for(Component component : components){
            if(originalY == -1){
                originalY = component.getOY();
            }else if(component.getOY() < originalY){
                originalY = component.getOY();
            }
        }
        return originalY;
    }
    
    @Override
    public int getW() {
        int w = -1;
        for(Component component : components){
            if(w == -1){
                w = component.getW();
            }else if(component.getW() > w){
                w = component.getW();
            }
        }
        return w;
    }

    @Override
    public int getH() {
        int h = -1;
        for(Component component : components){
            if(h == -1){
                h = component.getH();
            }else if(component.getH() > h){
                h = component.getH();
            }
        }
        return h;
    }
    
    @Override
    public int getFarX() {
        int x = -1;
        for(Component component : components){
            if(x == -1){
                x = component.getFarX();
            }else if(component.getFarX() > x){
                x = component.getFarX();
            }
        }
        return x;
    }

    @Override
    public int getFarY() {
        int y = -1;
        for(Component component : components){
            if(y == -1){
                y = component.getFarY();
            }else if(component.getFarY() > y){
                y = component.getFarY();
            }
        }
        return y;
    }
    
    @Override
    public AtomicReference<Composite> getGroup() {
        return this.parent;
    }

    @Override
    public void setGroup(AtomicReference<Composite> composite) {
        this.parent = composite;
    }

    /**
     * Accept function for visitor.
     * @param v Visitor to accept.
     */
    @Override
    public void Accept(Visitor v) {
        v.Visit(this);
//        components.forEach((Component component) -> component.Accept(v));
        components.parallelStream().forEach(c -> c.Accept(v));
    }
}
