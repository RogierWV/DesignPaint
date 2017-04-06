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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel{
    
     private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    
    int latestID = 0;
    
//    static final String SHAPE_RECTANGLE = "rectangle";
//    static final String SHAPE_ELLIPSE = "ellipse";
//    static final String MODE_MOVE = "move";
//    static final String MODE_SELECT = "select";
    static enum Mode {
        none,
        rectangle,
        ellipse,
        move,
        resize,
        select
    }
//    String selectedMode = "none";
    Mode selectedMode = Mode.none;
    JLabel keys = new JLabel("E for Ellipse / R for Rectangle / S for Select Mode / M for Move Mode");
    JLabel text = new JLabel("");
    
    List<Shape> shapes = new ArrayList();
    Shape select = new Select(-1, 0, 0, 0, 0);
    int selectedShape = -2;
    

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
                //moveSquare(e.getX(),e.getY());
                if(null != selectedMode) 
                switch (selectedMode) {
                    case rectangle:
                        newShape(Mode.rectangle, e.getX(), e.getY(), 1, 1);
                        break;
                    case ellipse:
                        newShape(Mode.ellipse, e.getX(), e.getY(), 1, 1);
                        break;
                    case select:
                        selectShape(e.getX(), e.getY());
                        break;
                    case move:
                        drawShape(Mode.ellipse, e.getX(), e.getY(), latestID-1);
                        break;
                    case resize:
                        break;
                    default:
                        break;
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                //moveSquare(e.getX(),e.getY());
                if(null != selectedMode) 
                switch (selectedMode) {
                    case rectangle:
                        drawShape(Mode.rectangle, e.getX(), e.getY(), latestID-1);
                        break;
                    case ellipse:
                        drawShape(Mode.ellipse, e.getX(), e.getY(), latestID-1);
                        break;
                    case select:
                        break;
                    case move:
                        break;
                    case resize:
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
                    case VK_ESCAPE:
                        text.setText("");
                        selectedMode = Mode.none;
                        break;
                }
              }
        });
        
    }
    
    private void newShape(Mode shape, int x, int y, int w, int h){
        Shape s = null;
         switch (shape) {
             case rectangle:
                 s = new Rectangle(latestID, x, y, w, h);
                 break;
             case ellipse:
                 s = new Ellipse(latestID, x, y, w, h);
                 break;
             case select:
                 Select sel = new Select(-1, x, y, w, h);
                 //latestID++;
                 select = sel;
                 break;
             default:
                 System.err.println("ERROR: Can't use current Mode for creating new Shapes!");
                 return;
         }
        latestID++;
        shapes.add(s);
        
        repaint();
    }
    
    private void drawShape(Mode shape, int w, int h, int shapeId){
        
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

        for (int i = 0; i < size; i++) {
            int farX = shapes.get(i).getCoordinateX() + shapes.get(i).getWidth();
            int farY = shapes.get(i).getCoordinateY() + shapes.get(i).getHeight();
            if(shapes.get(i).getCoordinateX() < x && shapes.get(i).getCoordinateY() < y && farX > x && farY > y){
                clearSelect();
                newShape(Mode.select, shapes.get(i).getCoordinateX()-1, shapes.get(i).getCoordinateY()-1, shapes.get(i).getWidth()+2, shapes.get(i).getHeight()+2);
                selectedShape = shapes.get(i).getId();
                break;
            }
        }
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
    
    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
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
    
}

