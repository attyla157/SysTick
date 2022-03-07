import javax.swing.*;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import java.awt.*;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.PopupMenu;
/**
 * Write a description of class ComponentTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ComponentTester extends JFrame
{
    // instance variables - replace the example below with your own
    private int x;
    JTrafficLight light;
    JPanel mainPanel;
    Button impuls;
    JCheckBox red,yellow,green;
    /**
     * Constructor for objects of class ComponentTester
     */
    public ComponentTester()
    {
         setSize(800 ,600);
        setTitle("Component tester");
        
        setVisible(true);
        
        initGUI();
        
        
        //mainPanel.setLayout(new FlowLayout());
        //mainPanel.add(light);
        //mainPanel.setBackground(Color.GREEN);
        validate();
    }
    void initGUI(){
        setLayout(new GridLayout(1,2));
        JPanel mainPanel = new JPanel();
        
        
        light = new JTrafficLight();
        add(light);
        setBackground(Color.GREEN);
        add(mainPanel);
        JCheckBox red = new JCheckBox("Control red");
        JCheckBox yellow = new JCheckBox("Control yellow");
        JCheckBox green = new JCheckBox("Control green");
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(red);
        mainPanel.add(yellow);
        mainPanel.add(green);
        red.setSelected(true);
        green.setSelected(true);
        yellow.setSelected(true);
        red.addActionListener((ActionEvent e) ->
        {
             if(red.isSelected()){    
                  light.setColorTop(Color.RED);
                  repaint();
                }    
            else{    
                   light.setColorTop(Color.GRAY);
                   repaint();
                } 
            
        });
        yellow.addActionListener((ActionEvent e) ->
        {
             if(yellow.isSelected()){    
                  light.setColorMid(Color.YELLOW);
                  repaint();
                }    
            else{    
                   light.setColorMid(Color.GRAY);
                   repaint();
                } 
            
        });
        green.addActionListener((ActionEvent e) ->
        {
             if(green.isSelected()){    
                  light.setColorBot(Color.GREEN);
                  repaint();
                }    
            else{    
                   light.setColorBot(Color.GRAY);
                   repaint();
                } 
            
        });
        
    }
    public static void main(String[] args)
    {
       new ComponentTester();
    }

    
}
