package designpaint;

import java.util.ArrayList;


public class Composite implements Component{
    private ArrayList<Component> components = new ArrayList<Component>();
    
    public void add(Component component)
    {
        components.add(component);
    }
    
    public void print()
    {
        for(Component component : components)
        {
            System.out.println("Ellipse");
        }
    }
}
