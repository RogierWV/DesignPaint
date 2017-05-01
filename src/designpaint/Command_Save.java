package designpaint;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Command to save a Composite (root object)
 * @see Composite
 * @see SaveVisitor
 */
public class Command_Save extends Command {
    
    Composite root;
    String name;
    
    /**
     * Creates a Command_Save for given root node
     * @param root Composite to save
     * @param name Filename to save to
     */
    public Command_Save(Composite root, String name){
        this.root = root;
        this.name = name;
    }

    /**
     * Actually saves the root to the given file
     */
    @Override
    public void execute() {
        Path path = FileSystems.getDefault().getPath(name);
        try
        {
            Files.write(path, 
                    "".getBytes(),
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        
        SaveVisitor save = new SaveVisitor("");
        root.Accept(save);
    }

    /**
     * NOT ALLOWED (undoing writing to a file would make no sense!)
     * @throws UnsupportedOperationException ALWAYS
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException("Can't undo save");
    }
    
}
