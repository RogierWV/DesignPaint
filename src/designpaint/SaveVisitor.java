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
public class SaveVisitor implements Visitor{
    
    String prefix;
    
    public SaveVisitor(String prefix){
        this.prefix = prefix;
        
    }

    @Override
    public void Visit(Component component) {
        String toSave = "";
        String name = "test.txt";
        
        toSave = component.toString()+"\r\n";
        Path path = FileSystems.getDefault().getPath(name);
        try
        {
            Files.write(path, 
                    toSave.getBytes(),
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}
