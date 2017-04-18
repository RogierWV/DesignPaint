package designpaint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class Composite implements Component{
    private ArrayList<Component> components = new ArrayList<>();
    
    public void Composite(){
        
    }
    
    public void add(Component component)
    {
        components.add(component);
    }
    
    @Override
    public void print(String prefix)
    {
        if(prefix == null){
            prefix = "-";
        }else{
            prefix = prefix.concat("-");
        }
        for(Component component : components)
        {
            component.print(prefix);
            //System.out.println("Ellipse");
        }
    }
   

    @Override
    public void resize(int offsetW, int offsetH) {
        for(Component component : components){
            component.resize(offsetW, offsetH);
        }
    }

    @Override
    public void move(int offsetX, int offsetY) {
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
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getOX() {
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getOY() {
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getW() {
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getH() {
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
