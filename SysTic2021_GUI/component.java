import javax.swing.* ;
import java.awt.Graphics ;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.* ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
/**
 * Write a description of class component here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class component extends JComponent implements MouseListener
{
    // instance variables - replace the example below with your own
    Color color = Color.RED;
    boolean status;
    Timer blinkTimer;
    ActionListener actionListener = null;
    
    public synchronized void addActionListener(ActionListener l){
         }

    /**
     * Constructor for objects of class component
     */
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        
        Dimension d = getSize();
        int width =  (int) d.getWidth();
        int height = (int) d.getHeight();
        if(status)
        {
           g.setColor(Color.BLUE); 
        }
        else
        {
            g.setColor(Color.RED); 
        }
        
        
        g.fillOval(10, 10, width, height);
        
        
    }
    public void setColor(Color color)
    {
       this.color = color;
       repaint();
    }
    public void setStatus(boolean status)
    {
        this.status = status;
        //if (status == true) blinkTimer.start();
        repaint();
        
    }
    ActionListener tastPerformer = new ActionListener()
    {
        public void actionPerformed(ActionEvent evt){
            setStatus(false);
        }
    };
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(30,30);
    }
    public Dimension getMinimumSize()  
    {
        return new Dimension(20,20);
    }
    public component()
    {   this.color = color;
        addMouseListener(this);
        //blinkTimer = new Timer(1000, this);
        //blinkTimer.setRepeats(false);
        // initialise instance variables
        
    }
    
    public boolean contains(int x , int y)
    {   Dimension d = getSize();
        double w = d.getWidth()/2;
        double h = d.getHeight()/2;
        double wdo2 = (x-w)*(x-w)/(w*w);
        double hdo2 = (y-w)*(y-h)/(w*w);
        
        return ((wdo2 + hdo2) <= 0.9);
    }
    public void mousePressed(MouseEvent me){
        status = !status;
        repaint();

    }
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseClicked(MouseEvent me){}

}
