package designpaint;

import java.util.concurrent.atomic.AtomicReference;

public class Command_Resize extends Command{
    AtomicReference<Component> component;
    int currentOffsetW;
    int currentOffsetH;
    int nextOffsetW;
    int nextOffsetH;
    public Command_Resize(AtomicReference<Component> component, int clickX, int clickY, int x, int y, int oldW, int oldH) {
        this.component = component;
        currentOffsetW = component.get().getOX() + component.get().getW() - oldW;
        currentOffsetH = component.get().getOY() + component.get().getH() - oldH;
        nextOffsetW = x - clickX;
        nextOffsetH = y - clickY;
        //shapeW = Math.abs(component.get().getW()) + component.get().getX() - oldW;
        //shapeH = Math.abs(component.get().getH()) + component.get().getY() - oldH;
        //System.out.println("Resize Constructor: "+ id);
    }

    @Override
    public void execute() {
//        System.out.println("nextOffsetW:"+w+" currentOffsetW: "+currentOffsetW+" next - current: "+ (nextOffsetW - currentOffsetW) + ""+""+""+""+"");
//        System.out.println(" Component getX: "+component.get().getX()+" Component getOX: "+component.get().getOX()+" + Component getW: "+ component.get().getW() + " - oldW: "+oldW+""+""+""+"");
        component.get().resize(nextOffsetW - currentOffsetW, nextOffsetH - currentOffsetH);
    }

    @Override
    public void undo() {
        currentOffsetW = 0;
        currentOffsetH = 0;
        component.get().resize(-nextOffsetW, -nextOffsetH);
    }
    
}
