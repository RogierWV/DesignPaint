package designpaint;

public interface Visitor {
    public void Visit(Component component);
    public void Visit(Composite composite);
}
