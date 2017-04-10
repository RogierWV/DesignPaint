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

public class FileIO {
    public static void save(List shapes, String name) {
        String toSave = (String)shapes.stream().map(Object::toString).collect(Collectors.joining("\r\n"));
        Path path = FileSystems.getDefault().getPath(name);
        try
        {
            Files.write(path, toSave.getBytes(),StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        }
        catch (IOException e)
        {
            System.err.print(e);
        }
    }
    
    public static List<Shape> load(String name) {
        Path path = FileSystems.getDefault().getPath(name);
        List<Shape> shapes = new ArrayList();
        int id = 0;
        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
              //System.out.println(line);
              String[] split = line.split(" ");
              switch(split[0]){
                  case "ellipse":
                      shapes.add(new Ellipse(id, Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4])));
                      break;
                  case "rectangle" :
                      shapes.add(new Rectangle(id, Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4])));
                      break;
                  default:
                      id--;
              }
              id++;
            }
          } catch (IOException e) {
            System.out.println(e);
          }
        
        
        return shapes;
    }
    
    
}
