package designpaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main component of the application.
 * Contains key/mouse listeners and base drawing logic.
 * User can draw shapes on this canvas.
 */
public class Canvas extends JPanel implements ActionListener{
    
    private int clickX;
    private int clickY;
    
    private int latestID;
    
    /**
     * Modes for mouse interaction.
     * options:
     *      none: Interaction does nothing.
     *      rectangle: Creates a rectangle on mouse click/drag.
     *      ellipse: Creates a ellipse on mouse click/drag.
     *      move: Moves the selected object on mouse drag.
     *      resize: Resizes the selected object on mouse drag.
     *      select: Selects an object on mouse click.
     */
    private static enum Mode {
        none,
        rectangle,
        ellipse,
        move,
        resize,
        select
    }
    private Mode selectedMode = Mode.none;
    //private JLabel keys;
    private JLabel text;
    
    private List<Shape> shapes = new ArrayList();
    private Composite root;
    private Shape select = new Select(-1, 0, 0, 0, 0);
    private AtomicReference<Component> selectedShape;
    
    private final Stack<Command> history;
    private final Stack<Command> future;
    
    private int anchorX;
    private int anchorY;
    
    /**
     * Creates an canvas.
     */
    public Canvas() {
        this.anchorY = 0;
        this.anchorX = 0;
        this.latestID = 0;
        this.clickY = 0;
        this.clickX = 0;
        
        this.history = new Stack<>();
        this.future = new Stack<>();
        this.selectedShape = new AtomicReference<>();
        
        /*keys = new JLabel("E for Ellipse / R for Rectangle / S for Select Mode"
                + " / M for Move Mode / Z for Resize Mode / F to save canvas \n / L to load canvas"
                + " / U to undo last action / I to redo last undo");*/
        text = new JLabel("");
        this.setFocusable(true);
        this.requestFocusInWindow();
        //this.add(keys);
        this.add(text);
//        this.keys.setLocation(10,10);
        this.text.setLocation(10,10);
        this.setLayout(null);
//        this.keys.setSize(900, 14);
        this.text.setSize(900, 14);
        setBorder(BorderFactory.createLineBorder(Color.black));

        mouse_press();
        mouse_drag();
        key_press();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Listeners">    
    /**
     * Listener that handles mouse presses. 
     */
    private void mouse_press() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                Command cmd;
                
                System.out.println("History size: " + history.size());
                if(selectedMode != null)
                switch (selectedMode) {
                    case rectangle:
                        future.clear();
                        cmd = new Command_AddRectangle(shapes, latestID, e.getX(), e.getY(), e.getX(), e.getY());
                        latestID++;
                        cmd.execute();
                        history.push(cmd);
                        break;
                    case ellipse:
                        future.clear();
                        cmd = new Command_AddEllipse(shapes, latestID, e.getX(), e.getY(), e.getX(), e.getY());
                        latestID++;
                        cmd.execute();
                        history.push(cmd);
                        break;
                    case move:
                        future.clear();
                        anchorX = selectedShape.get().getCoordinateX();
                        anchorY = selectedShape.get().getCoordinateY();
                        cmd = new Command_Move(shapes, selectedShape.get().getId(), e.getX(), e.getY(), clickX, clickY, anchorX, anchorY);
                        clickX = e.getX();
                        clickY = e.getY();
                        cmd.execute();
                        history.push(cmd);
                        break;
                    case resize:
                        future.clear();
                        if(selectedShape != null){
                            anchorX = selectedShape.get().getWidth() + selectedShape.get().getCoordinateX();
                            anchorY = selectedShape.get().getHeight() + selectedShape.get().getCoordinateY();
                            cmd = new Command_Resize(shapes, latestID, e.getX(), e.getY(), anchorX, anchorY);
                            latestID++;
                            cmd.execute();
                            history.push(cmd);
                            //drawSelect(rect);
                        }
                    case select:    
                        future.clear();
                        cmd = new Command_Select(shapes, selectedShape, e.getX(), e.getY());
                        cmd.execute();
                        history.push(cmd);
                        break;
                    default:
                        break;
                }
                repaint();
            }
        });
    }
    
    /**
     * Listener that handles mouse drags.
     */
    private void mouse_drag() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Command cmd;
                
                if(null != selectedMode) 
                switch (selectedMode) {
                    case rectangle:
                        removeDuplicateShapes(latestID - 1);
                        cmd = new Command_AddRectangle(shapes, latestID -1,clickX, clickY, e.getX(), e.getY());
                        cmd.execute();
                        history.pop();
                        history.push(cmd);
                        //drawSelect(rect);
                        repaint();
                        break;
                    case ellipse:
                        removeDuplicateShapes(latestID - 1);
                        cmd = new Command_AddEllipse(shapes, latestID-1, clickX, clickY, e.getX(), e.getY());
                        cmd.execute();
                        history.pop();
                        history.push(cmd);
                        repaint();
                        break;
                    case select:
                        break;
                    case move:
                        cmd = new Command_Move(shapes, selectedShape.get().getId(), e.getX(), e.getY(), clickX, clickY, anchorX, anchorY);
                        clickX = e.getX();
                        clickY = e.getY();
                        cmd.execute();
                        history.pop();
                        history.push(cmd);
                        break;
                    case resize:
                        if(selectedShape != null){
                            cmd = new Command_Resize(shapes, selectedShape.get().getId(), e.getX(), e.getY(), anchorX, anchorY);
                            cmd.execute();
                            history.pop();
                            history.push(cmd);
                            //drawSelect(rect);
                        }
                        break;
                    default:                        
                        break;
                }
                repaint();
            }
        });
    }
    
    /**
     * Listener that handles key presses.
     */
    private void key_press() {
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
    // </editor-fold>
    
    /**
     * removes duplicate shapes with same id.
     * @param shapeID ID to be checked for duplicates.
     */
    private void removeDuplicateShapes(int shapeID){
        for (Iterator<Shape> it = shapes.iterator(); it.hasNext(); ) {
            Shape shape = it.next();
            if (shape.getId() == shapeID)
                it.remove();
        }
    }    
    
    /**
     * Back door for selection function to create a box around the selected object.
     * @param shape Selected shape
     * @param x x coordinate of selected shape
     * @param y y coordinate of selected shape
     * @param w width of the selected shape
     * @param h height of the selected shape
     */
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
                 select = sel;
                 break;
             default:
                 System.err.println("ERROR");
                 break;
         } repaint();
    }
    
    /**
     * Handles the repaint operation.
     * @param g graphics component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        shapes.stream().map(s -> s.draw(g)).toArray();
        for(Shape shape : shapes){
            if(selectedShape != null)
                if(shape == selectedShape.get()){
                    newShape(Mode.select, shape.getCoordinateX()-1, shape.getCoordinateY()-1, shape.getWidth()+2, shape.getHeight()+2);
                    select.draw(g); 
                }
        }
    }  
    
    /**
     * Basic undo function.
     */
    private void undo() {
        Command cmd = history.pop();
        cmd.undo();
        future.push(cmd);
        repaint();
    }
    
    /**
     * Basic redo function.
     */
    private void redo() {
        Command cmd = future.pop();
        cmd.execute();
        history.push(cmd);
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()) {
            case "save":
                FileIO.save(shapes, "test.txt");
                break;
            case "load":
                shapes = FileIO.load("test.txt");
                latestID = shapes.size();
                repaint();
                break;
            case "ellipse":
                text.setText("Ellipse selected");
                selectedMode = Mode.ellipse;
                break;
            case "rectangle":
                text.setText("Rectangle selected");
                selectedMode = Mode.rectangle;
                break;
            case "select":
                text.setText("Select Mode");
                selectedMode = Mode.select;
                break;
            case "move":
                text.setText("Move Mode");
                selectedMode = Mode.move;
                break;
            case "resize":
                text.setText("Resize Mode");
                selectedMode = Mode.resize;
                break;    
            case "undo":
                undo();
                break;
            case "redo":
                redo();
                break;
            default:
                break;
        }
    }
    
    public void setSelected(AtomicReference<Shape> pointer) {
        this.selectedShape = pointer;
    }
}
