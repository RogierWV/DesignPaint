package designpaint;

/**
 *
 * @author Matthe
 */
public class MoveVisitor implements Visitor{
    
    private int offsetX;
    private int offsetY;
    
    public MoveVisitor(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    @Override
    public void Visit(Component component) {
        component.move(offsetX, offsetY);
    }
}
