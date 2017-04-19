package designpaint;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Matthe
 */
public class Command_Save extends Command{
    
    Composite root;
    String name;
    
    public Command_Save(Composite root, String name){
        this.root = root;
        this.name = name;
    }

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

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Can't undo save"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
