package designpaint;

import java.io.IOException;
import java.util.List;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Contains base logic for saving and loading images
 */
public class FileIO {
    /**
     * Saves the given List to a file with the given name.
     * @param root Group to save
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
        try {
            List<String> lines = Files.readAllLines(path);
            
            lines.remove(0);
            for(int i = 0; i < lines.size();) {
                i+=parse(lines,i,stack,newShape,rootRef);
            }
          } catch (IOException e) {
            System.err.println(e);
          }
        
        
        return root;
    }
    
    private static int parse(List<String> lines, int linesIndex, Stack<AtomicReference<Composite>> stack, AtomicReference<Component> newShape, AtomicReference<Composite> rootRef) {
        Command cmd;
        String line = lines.get(linesIndex);
        String[] split = line.trim().split(" ");

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
                stack.peek().get().add(newGroup);
                AtomicReference<Composite> ref = new AtomicReference(newGroup);
                stack.push(ref);
                int count = 1;
                for(int i = linesIndex+1; i <= linesIndex+Integer.parseInt(split[1]);) {
                    int linesProcessed = parse(lines, i, stack, newShape, rootRef);
                    count += linesProcessed;
                    i += linesProcessed;
                }
                stack.pop();
                return count;
            default:
                break;
        }
        return 1;
    }
    
}
