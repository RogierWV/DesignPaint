/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author danny
 */
public class Canvas extends JPanel{
    
     private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    
    static final String SHAPE_RECTANGLE = "rectangle";
    static final String SHAPE_ELLIPSE = "ellipse";
    static final String MODE_MOVE = "move";
    static final String MODE_SELECT = "select";
    String selectedMode = "none";
    JLabel keys = new JLabel("E for Ellipse / R for Rectangle / S for Select Mode / M for Move Mode");
    JLabel text = new JLabel("");
    

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
                    
                }else if(selectedMode == SHAPE_ELLIPSE){
                    
                }else if(selectedMode == MODE_SELECT){
                    
                }else if(selectedMode == MODE_MOVE){
                    
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
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
    
    private void newRectangle(int x, int y, int h, int w){
        
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
        //g.drawString("No Shape Selected --- E for Ellipse/R for Rectangle",10,20);
        g.setColor(Color.RED);
        g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        g.drawRect(squareX,squareY,squareW,squareH);
    }  
    
}

