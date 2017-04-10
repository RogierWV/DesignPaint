package designpaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel{
    
    private int clickX = 0;
    private int clickY = 0;
    
    int latestID = 0;
    
    static enum Mode {
        none,
        rectangle,
        ellipse,
        move,
        resize,
        select
    }
    Mode selectedMode = Mode.none;
    JLabel keys = new JLabel("E for Ellipse / R for Rectangle / S for Select Mode / M for Move Mode / Z for Resize Mode / F to save canvas / L to load canvas");
    JLabel text = new JLabel("");
    
    List<Shape> shapes = new ArrayList();
    Shape select = new Select(-1, 0, 0, 0, 0);
    int selectedShape = -2;
    
    Stack<Command> history;
    Stack<Command> future;
    

    public Canvas() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(keys);
        this.add(text);
        keys.setLocation(10,10);
        text.setLocation(10,25);
        this.setLayout(null);
        keys.setSize(900, 14);
        text.setSize(900, 14);

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                Command cmd;
                if(selectedMode != null)
                switch (selectedMode) {
                    case rectangle:
                        cmd = new Command_AddRectangle(shapes, latestID, e.getX(), e.getY(), 1, 1);
                        latestID++;
                        cmd.execute();
                        history.push(cmd);
                        break;
                    case ellipse:
                        cmd = new Command_AddEllipse(shapes, latestID, e.getX(), e.getY(), 1, 1);
                        latestID++;
                        cmd.execute();
                        history.push(cmd);
                        break;
                    case resize:
                        if(selectedShape >= 0){
                            cmd = new Command_Resize(shapes, latestID, e.getX(), e.getY());
                            latestID++;
                            cmd.execute();
                            history.push(cmd);
                            //drawSelect(rect);
                        }
                    case select:    
                        cmd = new Command_Select(shapes);
                        cmd.execute();
                        history.push(cmd);
                        break;
                    default:
                        break;
                }
            }
        });
        
       

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Command cmd;
                if(null != selectedMode) 
                switch (selectedMode) {
                    case rectangle:
                        if(selectedShape >= 0){
                            cmd = new Command_Resize(shapes, latestID, e.getX(), e.getY());
                            latestID++;
                            cmd.execute();
                            history.pop();
                            history.push(cmd);
                            //drawSelect(rect);
                        }
                        break;
                    case ellipse:
                        if(selectedShape >= 0){
                            cmd = new Command_Resize(shapes, latestID, e.getX(), e.getY());
                            latestID++;
                            cmd.execute();
                            history.pop();
                            history.push(cmd);
                            //drawSelect(rect);
                        }
                        break;
                    case select:
                        break;
                    case move:
                        cmd = new Command_Move(shapes, selectedShape, e.getX(), e.getY());
                        cmd.execute();
                        history.pop();
                        history.push(cmd);
                        break;
                    case resize:
                        if(selectedShape >= 0){
                            cmd = new Command_Resize(shapes, latestID, e.getX(), e.getY());
                            latestID++;
                            cmd.execute();
                            history.pop();
                            history.push(cmd);
                            //drawSelect(rect);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case VK_E:
                        text.setText("Ellipse selected");
                        selectedMode = Mode.ellipse;
                        break;
                    case VK_R:
                        text.setText("Rectangle selected");
                        selectedMode = Mode.rectangle;
                        break;
                    case VK_S:
                        text.setText("Select Mode");
                        selectedMode = Mode.select;
                        break;
                    case VK_M:
                        text.setText("Move Mode");
                        selectedMode = Mode.move;
                        break;
                    case VK_Z:
                        text.setText("Resize Mode");
                        selectedMode = Mode.resize;
                        break;    
                    case VK_F:
                        FileIO.save(shapes, "test.txt");
                        break;
                    case VK_L:
                        shapes = FileIO.load("test.txt");
                        latestID = shapes.size();
                        repaint(); 
                        break;
                    case VK_ESCAPE:
                        text.setText("");
                        selectedMode = Mode.none;
                        break;
                    case VK_U:
                        undo();
                        break;
                    case VK_I:
                        redo();
                        break;
                }
              }
        });
        
    }
    
//     private Mode shapeType(String shape){
//            String shapeType = shapes.get(selectedShape).getShapeType();
//            switch (shapeType) {
//             case "ellipse":
//                 return Mode.ellipse;
//             case "rectangle":
//                 return Mode.ellipse;
//             default:
//                 return Mode.none;
//         }
//        }
    
    private void newShape(Mode shape, int x, int y, int w, int h){
         switch (shape) {
             case rectangle:
                 Rectangle rect = new Rectangle(latestID, x, y, w, h);
                 latestID++;
                 shapes.add(rect);
                 break;
             case ellipse:
                 Ellipse ell = new Ellipse(latestID, x, y, w, h);
                 latestID++;
                 shapes.add(ell);
                 break;
             case select:
                 Select sel = new Select(-1, x, y, w, h);
                 //latestID++;
                 select = sel;
                 break;
             default:
                 System.err.println("ERROR");
                 break;
         }
        repaint();
    }
   
    
    private void drawShape(int w, int h, int shapeId){
        
        Shape rect = shapes.get(shapeId);
        int x = rect.getOriginX();
        int y = rect.getOriginY();
        //bereken height/width aan de hand van cursor locatie ten opzichte van origin (x,y)
        int height = h - y;
        int width = w - x;
        rect.setDimensions(x, y, width, height);
        repaint();
    }
    
    private void selectShape(int x, int y){
        int size = shapes.size();
        int area = -1;
        for (int i = 0; i < size; i++) {
            int farX = shapes.get(i).getCoordinateX() + shapes.get(i).getWidth();
            int farY = shapes.get(i).getCoordinateY() + shapes.get(i).getHeight();
            if(shapes.get(i).getCoordinateX() < x && shapes.get(i).getCoordinateY() < y && farX > x && farY > y){
                if(area == -1){
                    area = shapes.get(i).getArea();
                    clearSelect();
                    drawSelect(shapes.get(i));
                    selectedShape = shapes.get(i).getId();
                }else{
                    if(area > shapes.get(i).getArea()){
                        area = shapes.get(i).getArea();
                        clearSelect();
                        drawSelect(shapes.get(i));
                        selectedShape = shapes.get(i).getId();
                    }
                }
//                clearSelect();
//                drawSelect(shapes.get(i));
//                selectedShape = shapes.get(i).getId();
//                break;
            }
        }
    }
    
    private void drawSelect(Shape shape){
        newShape(Mode.select, shape.getCoordinateX()-1, shape.getCoordinateY()-1, shape.getWidth()+2, shape.getHeight()+2);
    }
    
    private void clearSelect(){
        int size = shapes.size();

        for (int i = 0; i < size; i++) {
            if("Select".equals(shapes.get(i).getClass().getSimpleName())){
                shapes.remove(i);
                break;
            }
        }
    }

    

     @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.RED);
//        g.fillRect(squareX,squareY,squareW,squareH);
//        g.setColor(Color.BLACK);
//        g.drawRect(squareX,squareY,squareW,squareH);
//        for (Shape shape: shapes) {
//            shape.draw(g);
//        }
        shapes.stream().map(s -> s.draw(g)).toArray();
        select.draw(g);
    }
    
    private void undo() {
        Command cmd = history.pop();
        cmd.undo();
        future.push(cmd);
    }
    
    private void redo() {
        Command cmd = future.pop();
        cmd.execute();
        history.push(cmd);
    }
    
}