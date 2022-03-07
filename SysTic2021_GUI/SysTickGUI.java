import javax.swing.*;
import javax.swing.BorderFactory;
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
 * Write a description of class SysTickGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SysTickGUI extends JFrame implements ActionListener,Observer 
{
    // instance variables - replace the example below with your own
    CortexM0SysTic sysTick;
    ImpulseSource generator;
    GeneratorMode mode;
    
    JLabel sliderLabel;
    Timer refreshTimer, interruptTimer;
    JPopupMenu popWindow;
    JTextField poleRVR, poleCVR, poleControlRVR, poleControlCVR, showInterrupt;
    JCheckBox fEnable,fCountFlag,fTickInt;
    JButton oneImpuls ,tenImpuls,  genOnn, genOff ;
    JSlider periodSlider;
    JComboBox choseMode;
    component led;
    JRadioButton controlEnable, controlTickInt;
    
    /**
     * Constructor for objects of class SysTickGUI
     */
    public SysTickGUI()
    {
        // initialise instance variables
        setSize(800 ,600);
        setTitle("SysTick Model");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);// przycisk zamknięcia nie zamyka okna
        setVisible(true);
        sysTick = new CortexM0SysTic(5,true);
        sysTick.addObserver(this);
        generator = new ImpulseSource();
        generator.addImpulsListener(sysTick);
         GeneratorMode mode = GeneratorMode.CONTINIOUS;
        
        initGUI() ;
       
        new Timer(16,taskPerformer).start();
        //Timer interruptTimer = new Timer(2000,interruptPerformer);
        //interruptTimer.start();//timer znikający napis 
        validate();
        
        
    }
    
    public void createMenuBar()
    {   
        JMenuBar menubar = new JMenuBar();
        //ImageIcon exitIcon = new ImageIcon("exit.png");
        
        JMenu fileMenu = new JMenu("Menu");
        //fileMenu.setMnemonic (KeyEvent.VK_F);
        fileMenu.add(new JSeparator());
        JMenuItem eMenuItem = new JMenuItem("Exit");
        JMenuItem info = new JMenuItem("Informacje");
        info.setToolTipText("Wyświetl informacje o programie i autorze");
        eMenuItem.setToolTipText("Exit aplication");
        eMenuItem.addActionListener((event ->{
            generator.interrupt();
            dispose();
            System.exit(0);
        }));
        info.addActionListener((event ->{
            
            JOptionPane.showInternalMessageDialog(null, "Program edukacyjny prezentujący  działanie licznika SysTick w procesorze z rdzeniem Cortex M0\n\r Autor: Adam Winiarski","Informacje", JOptionPane.INFORMATION_MESSAGE);
            
        }));
        
        fileMenu.add(info);
        fileMenu.add(eMenuItem);
        menubar.add(fileMenu);
        setJMenuBar(menubar);
        
   
        
    }
     ActionListener taskPerformer = new ActionListener() 
     {
            public void actionPerformed(ActionEvent evt) {
          //...Perform a task...
          showSysTickState();
        }
    };
    void initGUI()
    {
        createMenuBar();
        JPanel left,center,right,top,bottom;
        left = new JPanel();
        left.setBorder(BorderFactory.createRaisedBevelBorder());
        left.setBackground(Color.WHITE);
        left.setLayout(new GridLayout(0,1));
        
        center = new JPanel();
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createRaisedBevelBorder());
        
        right = new JPanel();
        right.setLayout(new FlowLayout());
        right.setBorder(BorderFactory.createRaisedBevelBorder());
        top = new JPanel();
        top.setLayout(new FlowLayout());
        Dimension d = top.getSize();
        d.height *=2;
        top.setMinimumSize(d);
        bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        
       // right.setBackground(Color.RED);
        
        setLayout(new BorderLayout());
        add(left,BorderLayout.WEST);
        
        add(center,BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);
        add(bottom,BorderLayout.SOUTH);
        
        poleCVR = new JTextField(8);
        poleCVR.setEditable(false);
        poleControlRVR = new JTextField(8);
        
        
        poleControlCVR = new JTextField(8);
        poleCVR.setFont(new Font(poleCVR.getFont().getName(),Font.BOLD, 18));
        poleCVR.setText("-----");
        poleRVR = new JTextField(8);
        poleRVR.setFont(new Font(poleRVR.getFont().getName(),Font.BOLD, 18));
        poleRVR.setEditable(false);
        poleRVR.setText("-----");
        
        
        JPanel panelRVR = new JPanel(new GridLayout(2,1));
        panelRVR.add(new JLabel("Rejestr RVR",JLabel.CENTER));
        JPanel panelRVR_1 = new JPanel(new GridLayout(2,2));
        panelRVR_1.setBorder(BorderFactory.createRaisedBevelBorder());
        panelRVR.add(panelRVR_1);
        JLabel obecnyRVR = new JLabel("Obecna wartość",JLabel.CENTER);
        obecnyRVR.setBorder(BorderFactory.createRaisedBevelBorder());
        JLabel ustawRVR = new JLabel("Ustaw RVR" ,JLabel.CENTER);
        ustawRVR.setBorder(BorderFactory.createRaisedBevelBorder());
        panelRVR_1.add(obecnyRVR);
        panelRVR_1.add(ustawRVR);
        panelRVR_1.add(poleRVR);
        panelRVR_1.add(poleControlRVR);
        panelRVR.setBorder(BorderFactory.createRaisedBevelBorder());
        
        
        top.add(panelRVR);
      
       
        
        
        
        JPanel flag = new JPanel();
        fEnable = new JCheckBox("Enable");
        fCountFlag = new JCheckBox("Count ");
        fTickInt = new JCheckBox("TickInt ");
        
        //left.add(flag);
        flag.setLayout( new GridLayout(6,2));
        flag.setPreferredSize(new Dimension(300, 500));
        //flag.setMinimumSize(new Dimension(300, 500));
        
        flag.add(fCountFlag);
        flag.add(fTickInt);
        flag.add(fEnable);
        fEnable.setEnabled(false);
        fTickInt.setEnabled(false);
        fCountFlag.setEnabled(false);
        
        
        controlTickInt = new JRadioButton("Tick Int");
       
        controlEnable = new JRadioButton("Enable",true);
        flag.add(controlEnable);
        flag.add(controlTickInt);
        JButton checkCountFlag = new JButton("Sprawdz flage CountFlag");
        flag.add(checkCountFlag);
        checkCountFlag.addActionListener((ActionEvent e) ->
        {
           String stan=String.valueOf(sysTick.isCountFlag());
             JOptionPane.showInternalMessageDialog(null, stan,"Stan flagi CountFlag", JOptionPane.INFORMATION_MESSAGE);
            
        });
        controlEnable.addActionListener((ActionEvent e) ->
        {
             if(controlEnable.isSelected()){    
                  sysTick.setEnableFlag(true);
                }    
            else{    
                   sysTick.setEnableFlag(false);
                } 
            
        });
        controlTickInt.addActionListener((ActionEvent a) ->
        {
             if(controlTickInt.isSelected()){    
                  sysTick.setTickInt(true);
                }    
             else{    
                   sysTick.setTickInt(false);
                } 
            
        });
        
        //center
        center.setLayout(new GridLayout(0,1));
        center.add(flag);
        oneImpuls = new JButton("Jeden Impuls");
        oneImpuls.addActionListener(this);
        tenImpuls = new JButton("Dziesięć Impulsów");
        
        JPanel panelCVR = new JPanel(new GridLayout(2,1));
        panelCVR.setBorder(BorderFactory.createRaisedBevelBorder());
        panelCVR.add(new JLabel("Rejestr CVR",JLabel.CENTER));
        JPanel panelCVR_1 = new JPanel(new GridLayout(2,2));
        panelCVR_1.setBorder(BorderFactory.createRaisedBevelBorder());
        panelCVR.add(panelCVR_1);
        JLabel obecnyCVR = new JLabel("Obecna wartość",JLabel.CENTER);
        obecnyCVR.setBorder(BorderFactory.createRaisedBevelBorder());
        JLabel ustawCVR = new JLabel("Ustaw CVR" ,JLabel.CENTER);
        ustawCVR.setBorder(BorderFactory.createRaisedBevelBorder());
        panelCVR_1.add(obecnyCVR);
        panelCVR_1.add(ustawCVR);
        panelCVR_1.add(poleCVR);
        panelCVR_1.add(poleControlCVR);
        panelCVR.setBorder(BorderFactory.createRaisedBevelBorder());
        
        top.add(panelCVR);
        
               
        
        poleControlCVR.addActionListener(new ActionListener()
            {//anonimowa klasa wewnętrzna 
                public void actionPerformed(ActionEvent e)
                {
                    sysTick.setCVR(Integer.parseInt(poleControlCVR.getText()));
                    showSysTickState();
                }
                
            });
        poleControlRVR.addActionListener(new ActionListener()
            {//anonimowa klasa wewnętrzna 
                public void actionPerformed(ActionEvent e)
                {
                    sysTick.setRVR(Integer.parseInt(poleControlRVR.getText()));
                    showSysTickState();
                }
                
            });
       left.add(oneImpuls,BorderLayout.CENTER);
        
        //right
        
        right.setLayout(new GridLayout(5,1));
        genOnn= new JButton("Włącz generator");
        genOff = new JButton("Wyłącz generator");
        
        right.add(genOnn);
        right.add(genOff);
        
        genOnn.addActionListener((ActionEvent e) ->
        {
             generator.on();
            
        });
         genOff.addActionListener((ActionEvent e) ->
        {
             generator.off();
            
        });
        JSlider periodSlider = new JSlider(1,1000,500);
        periodSlider.setMinorTickSpacing(2);  
        periodSlider.setMajorTickSpacing(200);  
        periodSlider.setPaintTicks(true);  
        periodSlider.setPaintLabels(true);  
        JPanel sliderPanel = new JPanel(new GridLayout(2,1));
        
        JLabel sliderLabel = new JLabel("Okres jednego impulsu [ms]", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sliderPanel.add(sliderLabel);
        sliderPanel.add(periodSlider);
        right.add(sliderPanel);
        
        periodSlider.addChangeListener((e)->{
            
            generator.setPeriod(periodSlider.getValue());
            
        });
        
        
        
        component led = new component();
        //led.setStatus(true);
        //left.add(led);
        //opmenu
        //JPopupMenu popWindow = new JPopupMenu("Przerwanie  !");
        
        
        
       
         DefaultComboBoxModel<GeneratorMode> cModel = new DefaultComboBoxModel<>(GeneratorMode.values());
          JComboBox<GeneratorMode> choseMode = new JComboBox<>(cModel);
        
        JPanel menuGenerator_1 = new JPanel( new GridLayout(2,1));
        JLabel trybGeneratora = new JLabel("Tryb pracy generatora",JLabel.CENTER);
        
        menuGenerator_1.add(trybGeneratora);
        JPanel menuGenerator_2 = new JPanel(new GridLayout(1,2));
        menuGenerator_2.add(choseMode);
        JTextField nCycles  = new JTextField();
        nCycles.setEditable(false);
        menuGenerator_2.add(nCycles);
        menuGenerator_1.add(menuGenerator_2);
        right.add(menuGenerator_1);
        
        
        
         nCycles.addActionListener((e)->{
            int temp = Integer.parseInt(nCycles.getText());
            generator.setBurstCount(temp);
            
        });
        
        choseMode.addActionListener((e)->{
            GeneratorMode temp = (GeneratorMode)choseMode.getSelectedItem();
            generator.setMode(temp);
            if(temp==GeneratorMode.CONTINIOUS){
                nCycles.setEditable(false);
            }
            else{
                nCycles.setEditable(true);
            }
            
        });
        
        
    }
    public void actionPerformed(ActionEvent e)
    {  
        //e.getSource()  i potem switch case sprawdzamy który obiekt daje akcje
        sysTick.clock();
        showSysTickState();
        
    }
    
    public void update(Observable subject, Object arg)
    {
        
        JOptionPane.showInternalMessageDialog(null, "Uwaga, nastąpiło przerwanie ","Przerwanie", JOptionPane.WARNING_MESSAGE);
        //popWindow.show(showInterrupt,0,0);
        //led.setStatus(true);
        //sysTick.isCountFlag();
    }
    
    private void showSysTickState()
    {
        poleCVR.setText(""+sysTick.getCVR());
        poleRVR.setText(""+sysTick.getRVR());
        //fEnable,fCountFlag,fTickInt;
        fEnable.setSelected(sysTick.isEnableFlag());
        fCountFlag.setSelected(sysTick.inspectCountFlag());
        fTickInt.setSelected(sysTick.isTickIntFlag());
        
        
    }
    
    //klasa wiodąca
    public static void main(String[] args)
    {   EventQueue.invokeLater(()->
        {
        new SysTickGUI();
         });
    }
    
}
