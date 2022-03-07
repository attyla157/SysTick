import java.util.Observable;
import java.util.Observer;
/**
 * Write a description of class SysTickConsoleShow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SysTickConsoleShow implements Observer 
{
    // instance variables - replace the example below with your own
    private CortexM0SysTic sysTick;

    /**
     * Constructor for objects of class SysTickConsoleShow
     */
    public SysTickConsoleShow()
    {   
        sysTick = new CortexM0SysTic(5,true);
        sysTick.addObserver(this);
        makeShow();
    }
    public void makeShow()
    {
        System.out.println("\f Start");
    
        sysTick.setTickInt(true);
        System.out.println(sysTick);
        System.out.println("Wysyłam 20 impulsów");
        
        for (int i =0;i<20; i++) sysTick.clock();
        System.out.println("Po 20 impulsach");
 
        System.out.println(sysTick);
        System.out.println("Ustawiam RVR na 10 i Wysyłam 20 impulsów");
        sysTick.setRVR(10);
        System.out.println(sysTick);
        for (int i =0;i<20; i++) sysTick.clock();
        System.out.println("Po 20 impulsach");
        //dodaj przerwanie 
        System.out.println(sysTick);
        
    }
    public void update(Observable subject, Object arg)
    {
        System.out.println("PRZERWANIE od " + subject);
    }
    
    public static void main(String[] args)
    {
       new  SysTickConsoleShow();
    }
}
