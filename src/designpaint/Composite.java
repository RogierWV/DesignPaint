package designpaint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


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
    
    public List<Component> toFlatList() {
        return Arrays.asList((Component[]) components.stream().map((c) -> c.toFlatList()).toArray());
    }

    @Override
    public GroupListItem toListItem() {
        return new GroupListItem(new AtomicReference<>(this));
    }
}
