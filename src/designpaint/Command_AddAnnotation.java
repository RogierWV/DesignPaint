package designpaint;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 */
public class Command_AddAnnotation extends Command {
    
    Component sub;
    String text;
    AtomicReference<Composite> group;
    Annotation n;
    AtomicReference<Component> self;
    AtomicReference<Component> oldRef;

    public Command_AddAnnotation(Component sub, String text, String loc, AtomicReference<Component> self) {
        this.sub = sub;
        System.out.println(sub);
        this.text = text;
        this.group = sub.getGroup();
        switch(loc) {
            case "top":
                this.n = new AnnotationTop(text, sub);
                break;
            case "left":
                this.n = new AnnotationLeft(text, sub);
                break;
            case "right":
                this.n = new AnnotationRight(text, sub);
                break;
            case "bottom":
                this.n = new AnnotationBottom(text, sub);
                break;
            default:
                break;
        }
        this.self = self;
    }

    

    public Command_AddAnnotation(Component sub, String text, String loc) {
        this(sub,text,loc,new AtomicReference<Component>());
    }

    @Override
    public void execute() {
        if(group.get().contains(sub))
            group.get().remove(sub);
        group.get().add(n);
        if(self!=null) {
            oldRef = new AtomicReference<>(self.get());
            self.set(n);
        }
    }

    @Override
    public void undo() {
        group.get().remove(n);
        group.get().add(sub);
        if(self!=null) self.set(oldRef.get());
    }
    
}
