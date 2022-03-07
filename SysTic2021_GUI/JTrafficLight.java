import javax.swing.* ;
import java.awt.Graphics ;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.* ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
/**
/**
 * Write a description of class JTrafficLight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JTrafficLight extends JComponent implements MouseListener
{
    // instance variables - replace the example below with your own
    private int x;
    Color colorTop = Color.RED;
    Color colorMid = Color.YELLOW;
    Color colorBot = Color.GREEN;
    
    /**
     * Constructor for objects of class JTrafficLight
     */
    public void paintComponent(Graphics g){
        Dimension d = getSize();
        int width =  (int) d.getWidth();
        int height = (int) d.getHeight();
        g.setColor(Color.BLACK);
        g.fill3DRect(10, 10,width-20, height-20, true);
        g.setColor(colorTop); 
        g.fillOval((width-20)/2-(height-20)/6, 10, (width-20)/3, (height-20)/3);
        g.setColor(colorMid);
        g.fillOval((width-20)/2-(height-20)/6, 10+(height-20)/3, (width-20)/3, (height-20)/3);
        g.setColor(colorBot);
        g.fillOval((width-20)/2-(height-20)/6, 10+2*(height-20)/3, (width-20)/3, (height-20)/3);
        
        
    }
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(200,600);
    }
    public Dimension getMinimumSize()  
    {
        return new Dimension(100,300);
    }
     public void setColorTop(Color color)
    {
       this.colorTop = color;
       repaint();
    }
     public void setColorMid(Color color)
    {
       this.colorMid = color;
       repaint();
    }
     public void setColorBot(Color color)
    {
       this.colorBot = color;
       repaint();
    }
    
    public JTrafficLight()
    {
        // initialise instance variables
        x = 0;
    }

    
    public void mousePressed(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseClicked(MouseEvent me){}
   
}
