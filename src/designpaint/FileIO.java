package designpaint;

//import designpaint.Shape;
//import designpaint.Ellipse;
//import designpaint.Rectangle;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Contains base logic for saving and loading images
 */
public class FileIO {
    /**
     * Saves the given List to a file with the given name.
     * @param root
     * @param shapes List of shapes to save
     * @param name Name of the file (relative to working directory)
     */
    public static void save(Composite root, String name) {
        //String toSave = (String)shapes.stream().map(Object::toString).collect(Collectors.joining("\r\n"));
        String toSave = root.print("");
        System.out.println(toSave);
        Path path = FileSystems.getDefault().getPath(name);
        
        try
        {
            Files.write(path, 
                    toSave.getBytes(),
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
    
    /**
     * Loads a list of shapes from a file with the given name.
     * @param name Name of the file (relative to working directory)
     * @return List of loaded shapes
     */
    public static Composite load(String name) {
        Path path = FileSystems.getDefault().getPath(name);
        Composite root = new Composite();
        Stack<AtomicReference<Composite>> stack = new Stack();
        AtomicReference<Composite> rootRef = new AtomicReference(root);
        stack.add(rootRef);
        AtomicReference<Component> newShape = new AtomicReference();
        Command cmd;
        //List<Shape> shapes = new ArrayList();
        int id = 0;
        try {
            System.out.println("WE GAAN LADEN BOIIIIIIIZ");
            List<String> lines = Files.readAllLines(path);
           
            int count = 0;
             lines.remove(0);
            for (String line : lines) {
                System.out.println(count);
                count++;
                //System.out.println(line);
                int level = line.indexOf(line.trim())/4;
                
                if(stack.size() != level){
                    stack.pop();
                }
                
                String[] split = line.trim().split(" ");
                System.out.println(line);
                
                switch(split[0]){
                    case "ellipse":
                        cmd = new Command_AddEllipse(
                                stack.peek(), 
                                newShape, 
                                Integer.parseInt(split[1]), 
                                Integer.parseInt(split[2]), 
                                Integer.parseInt(split[3]), 
                                Integer.parseInt(split[4]));
                        cmd.execute();
                        break;
                    case "rectangle" :
                        cmd = new Command_AddRectangle(
                                stack.peek(), 
                                newShape, 
                                Integer.parseInt(split[1]), 
                                Integer.parseInt(split[2]), 
                                Integer.parseInt(split[3]), 
                                Integer.parseInt(split[4]));
                        cmd.execute();
                        break;
                    case "group" :
                        Composite newGroup = new Composite();
                        AtomicReference<Composite> ref = new AtomicReference(newGroup);
                        stack.add(ref);
                        //root.add()
                    default:
                        id--;
              }
              id++;
            }
          } catch (IOException e) {
            System.err.println(e);
          }
        
        
        return root;
    }
    
    
}
