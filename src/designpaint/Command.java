package designpaint;

public abstract class Command {
    protected Command(){}
    
    /**
     * Execute this command
     */
    public abstract void execute();
    
    /**
     * Undo this command
     */
    public abstract void undo();
}
