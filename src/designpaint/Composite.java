package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class Composite implements Component{
    private ArrayList<Component> components = new ArrayList<>();
    private AtomicReference<Composite> parent;
    
    public void Composite(){
        
    }
    
    public void add(Component component, boolean group)
    {
        if(!group)
            component.setGroup(new AtomicReference<>(this));
        components.add(component);
    }
    
    @Override
    public String print(String prefix){
        String result = prefix + toString();
        //System.out.println(this.toString()); 
        if(prefix.equals("")){
            prefix = "    ";
        }else{
            prefix = prefix.concat("    ");
        }
        for(Component component : components)
        {
            result = result + "\r\n" + component.print(prefix);
            //System.out.println("Ellipse");
        }
        return result;
    }
    
    public String toString(){
        return "group " + components.size();
    }
   

    @Override
    public void resize(int offsetW, int offsetH) {
        for(Component component : components){
            component.resize(offsetW, offsetH);
        }
    }

    @Override
    public void move(int offsetX, int offsetY) {
        System.out.println(offsetX);
        for(Component component : components){
            component.move(offsetX, offsetY);
        }
    }
    
    public void remove(Component component){
        components.remove(component);
    }
    
    @Override
    public Component select(int x, int y){
        ArrayList<Component> selectableShapes;
        Component selected = null;
        for(Component component : components){
            if(selected == null){
                selected = component.select(x, y);
            }else if(component.getSmallestArea(x, y) < selected.getSmallestArea(x, y) && component.getSmallestArea(x, y) != -1){
                selected = component.select(x, y);
            }
        }
        return selected;
    }

    @Override
    public int getSmallestArea(int x, int y) {
        int smallestArea = -1;
        for(Component component : components){
            if(smallestArea == -1){
                smallestArea = component.getSmallestArea(x, y);
            }else if(component.getSmallestArea(x, y) < smallestArea){
                smallestArea = component.getSmallestArea(x, y);
            }
        }
        return smallestArea;
    }

    @Override
    public void draw(Graphics g) {
        for(Component component : components){
            component.draw(g);
        }
    }
    
    public List<GroupListItem> toFlatList(String prefix) {
//        return Arrays.asList((Component[]) components.stream().map((c) -> c.toFlatList()).toArray());
        List<GroupListItem> ret = new ArrayList<>();
        ret.add(this.toListItem(prefix));
        for(Component c : components) {
            ret.addAll(c.toFlatList(prefix+"--"));
        }
        return ret;
    }

    @Override
    public GroupListItem toListItem(String prefix) {
        return new GroupListItem(new AtomicReference<>(this), prefix+"Group");
    }

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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void Accept(Visitor v) {
        //v.Visit(this);
        for(Component component : components){
            component.Accept(v);
        }
    }
    
    public AtomicReference<Composite> getGroup() {
        return this.parent;
    }

    @Override
    public void setGroup(AtomicReference<Composite> composite) {
        this.parent = composite;
    }

    
}
