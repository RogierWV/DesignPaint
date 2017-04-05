package designpaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    
    static final String SHAPE_RECTANGLE = "rectangle";
    static final String SHAPE_ELLIPSE = "ellipse";
    static final String MODE_MOVE = "move";
    static final String MODE_SELECT = "select";
    String selectedMode = "none";
    JLabel keys = new JLabel("E for Ellipse / R for Rectangle / S for Select Mode / M for Move Mode");
    JLabel text = new JLabel("");
    
    List<Shape> shapes = new ArrayList();
    

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
                if(selectedMode == SHAPE_RECTANGLE){
                    newShape(SHAPE_RECTANGLE, e.getX(), e.getY(), 1, 1);
                }else if(selectedMode == SHAPE_ELLIPSE){
                    newShape(SHAPE_ELLIPSE, e.getX(), e.getY(), 1, 1);
                }else if(selectedMode == MODE_SELECT){
                    
                }else if(selectedMode == MODE_MOVE){
                    
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                //moveSquare(e.getX(),e.getY());
                if(selectedMode == SHAPE_RECTANGLE){
                    drawShape(SHAPE_RECTANGLE, e.getX(), e.getY());
                }else if(selectedMode == SHAPE_ELLIPSE){
                    drawShape(SHAPE_ELLIPSE, e.getX(), e.getY());
                }else if(selectedMode == MODE_SELECT){
                    
                }else if(selectedMode == MODE_MOVE){
                    
                }
            }
        });
        
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyChar() == 'e') {
                   text.setText("Ellipse selected");
                   selectedMode = SHAPE_ELLIPSE;
                }
                if (evt.getKeyChar() == 'r') {
                   text.setText("Rectangle selected");
                   selectedMode = SHAPE_RECTANGLE;
                }
                if (evt.getKeyChar() == 's') {
                   text.setText("Select Mode");
                   selectedMode = MODE_SELECT;
                }
                if (evt.getKeyChar() == 'm') {
                   text.setText("Move Mode");
                   selectedMode = MODE_MOVE;
                }
              }
        });
        
    }
    
    private void newShape(String shape, int x, int y, int w, int h){
        if(shape.equals(SHAPE_RECTANGLE)){
            Rectangle rect = new Rectangle(latestID, x, y, w, h);
            
            latestID++;
            shapes.add(rect);
        }else if(shape.equals(SHAPE_ELLIPSE)){
            Ellipse ell = new Ellipse(latestID, x, y, w, h);
            
            latestID++;
            shapes.add(ell);
        }else {System.err.println("ERROR");}
        
        repaint(x, y, w, h);
    }
    
    private void drawShape(String shape, int w, int h){
        
        Shape rect = shapes.get(latestID-1);
        int x = rect.getOriginX();
        int y = rect.getOriginY();
        //bereken height/width aan de hand van cursor locatie ten opzichte van origin (x,y)
        int height = h - y;
        int width = w - x;
        rect.setDimensions(x, y, width, height);
        repaint();
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
    

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.RED);
//        g.fillRect(squareX,squareY,squareW,squareH);
//        g.setColor(Color.BLACK);
//        g.drawRect(squareX,squareY,squareW,squareH);
        for (Shape shape: shapes) {
            shape.draw(g);
        }
    }  
    
}

