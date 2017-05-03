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

    public Command_AddAnnotation(Component sub, String text) {
        this.sub = sub;
        this.text = text;
        this.group = sub.getGroup();
        this.n = new Annotation(text, sub);
    }

    @Override
    public void execute() {
        if(group.get().contains(sub))
            group.get().remove(sub);
        group.get().add(n);
    }

    @Override
    public void undo() {
        group.get().remove(n);
        group.get().add(sub);
    }
    
}
