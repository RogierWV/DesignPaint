package designpaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class Canvas extends JPanel implements ActionListener {
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
    private Mode selectedMode;
    private final JLabel text;
    
    private Shape select = new Select(0, 0, 0, 0);
    private AtomicReference<Component> selectedShape;
    private final AtomicReference<Composite> selectedGroup;
    private final AtomicReference<Component> newShape;
    private final AtomicReference<Composite> rootRef;
    
    private final Stack<Command> history;
    private final Stack<Command> future;
    
    private int clickX;
    private int clickY;
    private int oldX;
    private int oldY;
    private int oldW;
    private int oldH;
    
    /**
     * Creates an canvas.
     */
    public Canvas() {
        //initializers - zero
        this.selectedMode = Mode.none;
        this.clickX = 0;
        this.clickY = 0;
        this.oldX = 0;
        this.oldY = 0;
        this.oldW = 0;
        this.oldH = 0;
        
        //initializers - new
        this.history = new Stack<>();
        this.future = new Stack<>();
        Composite root = new Composite();
        this.rootRef = new AtomicReference<>(root);
        this.rootRef.get().setGroup(rootRef);
        this.selectedShape = new AtomicReference();
        this.selectedGroup = new AtomicReference();
        this.newShape = new AtomicReference();
        this.selectedShape.set(rootRef.get());
        this.selectedGroup.set(rootRef.get());
        this.newShape.set(new Rectangle(0, 0, 0, 0));
        
        //initializers - GUI
        text = new JLabel("");
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(text);
        this.text.setLocation(10,10);
        this.setLayout(null);
        this.text.setSize(900, 14);
        setBorder(BorderFactory.createLineBorder(Color.black));

        //initializers - listeners
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
            @Override
            public void mousePressed(MouseEvent e) {
                clickX = e.getX();
                clickY = e.getY();
                Command cmd;
                
                System.out.println("History size: " + history.size());
                if(selectedMode != null)
                switch (selectedMode) {
                    case rectangle:
                        future.clear();
                        cmd = new Command_AddRectangle(selectedGroup, newShape, e.getX(), e.getY(), e.getX(), e.getY());
                        cmd.execute();
                        history.push(cmd);
                        oldW = newShape.get().getX() + newShape.get().getW();
                        oldH = newShape.get().getY() + newShape.get().getH();
                        break;
                    case ellipse:
                        future.clear();
                        cmd = new Command_AddEllipse(selectedGroup, newShape, e.getX(), e.getY(), e.getX(), e.getY());
                        cmd.execute();
                        history.push(cmd);
                        oldW = newShape.get().getX() + newShape.get().getW();
                        oldH = newShape.get().getY() + newShape.get().getH();
                        break;
                    case move:
                        if(selectedShape != null){
                            future.clear();
                            oldX = selectedShape.get().getX();
                            oldY = selectedShape.get().getY();
                            cmd = new Command_Move(selectedShape, clickX, clickY, e.getX(), e.getY(), oldX, oldY);
                            cmd.execute();
                            history.push(cmd);
                            } break;
                    case resize:
                        if(selectedShape != null){
                            future.clear();
                            int w = selectedShape.get().getW();
                            int h = selectedShape.get().getH();
                            if(w < 0)
                                w = 0;
                            if(h < 0)
                                h = 0;
                            oldW = selectedShape.get().getX() + w;
                            oldH = selectedShape.get().getY() + h;
                            cmd = new Command_Resize(selectedShape, clickX, clickY, e.getX(), e.getY(), oldW, oldH);
                            cmd.execute();
                            history.push(cmd);
                            //drawSelect(rect);
                        } break;
                    case select:    
                        future.clear();
                        cmd = new Command_Select(rootRef.get(), selectedShape, e.getX(), e.getY());
                        cmd.execute();
                        history.push(cmd);
                        break;
                    default:
                        break;
                } repaint();
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
                        cmd = new Command_Resize(newShape, clickX, clickY, e.getX(), e.getY(), oldW, oldH);
                        cmd.execute();
                        if(history.peek().getClass().getSimpleName().equals("Command_Resize")){
                            
                        }

                        break;
                    case ellipse:
                        cmd = new Command_Resize(newShape, clickX, clickY, e.getX(), e.getY(), oldW, oldH);
                        cmd.execute();                     
                        break;
                    case select:
                        break;
                    case move:
                        if(selectedShape.get() != null){
                        cmd = new Command_Move(selectedShape, clickX, clickY, e.getX(), e.getY(), oldX, oldY);
                        cmd.execute();
                        history.pop();
                        history.push(cmd);
                        } break;
                    case resize:
                        if(selectedShape.get() != null){
                            cmd = new Command_Resize(selectedShape, clickX, clickY, e.getX(), e.getY(), oldW, oldH);
                            cmd.execute();
                            history.pop();
                            history.push(cmd);
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
                        Command cmd = new Command_Save(rootRef.get(), "test.txt");
                        cmd.execute();
                        break;
                    case VK_L:
                        rootRef.set(FileIO.load("test.txt"));
                        selectedShape.set(rootRef.get());
                        selectedGroup.set(rootRef.get());
                        rootRef.get().setGroup(rootRef);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "save":
                Command cmd = new Command_Save(rootRef.get(), "test.txt");
                cmd.execute();
                break;
            case "load":
                rootRef.set(FileIO.load("test.txt"));
                selectedShape.set(rootRef.get());
                selectedGroup.set(rootRef.get());
                rootRef.get().setGroup(rootRef);
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
            case "group":
                System.out.println("Selectedshape: " + selectedShape.get());
                System.out.println("`----> Group: " + selectedShape.get().getGroup());
                Composite newGroup = new Composite();
                newGroup.setGroup(new AtomicReference<>(newGroup));
                System.out.println(selectedShape.get().getGroup());
                selectedShape.get().getGroup().get().add(newGroup);
                selectedShape.set(newGroup);
                selectedGroup.set(newGroup);
                //root.add(new Composite());
                break;
            default:
                break;
        }
    }
    // </editor-fold>
    
    
    /**
     * Handles the repaint operation.
     * @param g graphics component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rootRef.get().draw(g);
        int x = selectedShape.get().getX();
        int y = selectedShape.get().getY();
        int w = selectedShape.get().getFarX() - x;
        int h = selectedShape.get().getFarY() - y;
        select = new Select(x, y, Math.abs(w), Math.abs(h));
        select.draw(g);
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
    
    public void setSelected(AtomicReference<Component> pointer) {
        this.selectedShape = pointer;
    }
    
    AtomicReference<Composite> getRoot() {
        return rootRef;
    }
}
