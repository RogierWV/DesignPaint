package DesignPaint;

import designpaint.Shape;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
        return null;
    }
}
