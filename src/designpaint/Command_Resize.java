package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class Command_Resize extends Command{
    AtomicReference<Component> component;
    Component copy;
    int w;
    int h;
    int currentOffsetW;
    int currentOffsetH;
    int nextOffsetW;
    int nextOffsetH;
    int oldH;
    int oldW;
    public Command_Resize(AtomicReference<Component> component, int clickX, int clickY, int x, int y, int oldW, int oldH) {
        this.component = component;
        this.copy = copy;
        this.oldW = oldW;
        this.oldH = oldH;
        currentOffsetW = component.get().getOX() + component.get().getW() - oldW;
        currentOffsetH = component.get().getOY() + component.get().getH() - oldH;
        nextOffsetW = x - clickX;
        nextOffsetH = y - clickY;
        //shapeW = Math.abs(component.get().getW()) + component.get().getX() - oldW;
        //shapeH = Math.abs(component.get().getH()) + component.get().getY() - oldH;
        w = x - clickX;
        h = y - clickY;
        //System.out.println("Resize Constructor: "+ id);
    }

    @Override
    public void execute() {
        //System.out.println("nextOffsetW:"+w+" currentOffsetW: "+currentOffsetW+" next - current: "+ (nextOffsetW - currentOffsetW) + ""+""+""+""+"");
        System.out.println(" Component getX: "+component.get().getX()+" + Component getW: "+ component.get().getW() + " - oldW: "+oldW+""+""+""+"");
        component.get().resize(nextOffsetW - currentOffsetW, nextOffsetH - currentOffsetH);
                //System.out.println("Resize: " + id + " "+ w + " " + h);
    }

    @Override
    public void undo() {
        component.get().resize(-nextOffsetW, -nextOffsetH);
    }
    
}
