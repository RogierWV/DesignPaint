package designpaint;

public class ResizeVisitor implements Visitor{
    
    private int offsetW;
    private int offsetH;
    
    public ResizeVisitor(int offsetW, int offsetH){
        this.offsetW = offsetW;
        this.offsetH = offsetH;
    }

    @Override
    public void Visit(Component component) {
        component.resize(offsetW, offsetH);
    }
}
