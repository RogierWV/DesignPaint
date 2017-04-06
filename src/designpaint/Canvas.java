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
    
    private int clickX = 0;
    private int clickY = 0;
    
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
    JLabel keys = new JLabel("E for Ellipse / R for Rectangle / S for Select Mode / M for Move Mode / Z for Resize Mode");
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
                clickX = e.getX();
                clickY = e.getY();
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
                        if(selectedShape >= 0){
                            Shape rect = shapes.get(selectedShape);
                            int offsetX = e.getX() - clickX;
                            int offsetY = e.getY() - clickY;
                            rect.moveShape(offsetX, offsetY);
                            drawSelect(rect);
                        }
                        break;
                    case resize:
                        if(selectedShape >= 0){
                            drawShape(e.getX(), e.getY(), selectedShape);
                            Shape rect = shapes.get(selectedShape);
                            drawSelect(rect);
                        }
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
                        drawShape(e.getX(), e.getY(), latestID-1);
                        break;
                    case ellipse:
                        drawShape(e.getX(), e.getY(), latestID-1);
                        break;
                    case select:
                        break;
                    case move:
                        if(selectedShape >= 0){
                            Shape rect = shapes.get(selectedShape);
                            int originX = rect.getOriginX();
                            int originY = rect.getOriginY();
                            int offsetX = e.getX() - clickX;
                            int offsetY = e.getY() - clickY;
                            int width = rect.getWidth();
                            System.out.println("X:"+originX+" + "+offsetX+" || Width: "+width);
                            rect.setDimensions(originX + offsetX, originY + offsetY, rect.getWidth(), rect.getHeight());
                            System.out.println(rect.getOriginX());
                            System.out.println(rect.getCoordinateX());
                            //rect.moveShape(offsetX, offsetY);
                            drawSelect(rect);
                            repaint();
                            
                            clickX = e.getX();
                            clickY = e.getY();
                        }
                        break;
                    case resize:
                        if(selectedShape >= 0){
                            drawShape(e.getX(), e.getY(), selectedShape);
                            Shape rect = shapes.get(selectedShape);
                            drawSelect(rect);
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
                    case VK_ESCAPE:
                        text.setText("");
                        selectedMode = Mode.none;
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

        for (int i = 0; i < size; i++) {
            int farX = shapes.get(i).getCoordinateX() + shapes.get(i).getWidth();
            int farY = shapes.get(i).getCoordinateY() + shapes.get(i).getHeight();
            if(shapes.get(i).getCoordinateX() < x && shapes.get(i).getCoordinateY() < y && farX > x && farY > y){
                clearSelect();
                drawSelect(shapes.get(i));
                selectedShape = shapes.get(i).getId();
                break;
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
    
}