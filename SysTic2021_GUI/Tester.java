import java.util.Observable;
import java.util.Observer;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester implements Observer
{
    // instance variables - replace the example below with your own
    private CortexM0SysTic simulator;

    /**
     * Constructor for objects of class Tester
     */
    public Tester()
    {
        // initialise instance variables
        simulator = new CortexM0SysTic(10,10,true);
        simulator.addObserver(this);//dodanie obserwatora do przerwania 
        test();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void display(String text)
    {
        System.out.println(text);
    }
    public void update(Observable subject, Object arg)
    {
        display("PRZERWANIE");
    }
    void test()
    {
        display("text");
        simulator.clock();
        simulator.setEnableFlag(true);
        display("wartosc rejestru CVR "+simulator.getCVR());
        display("wartosc rejestru RVR"+simulator.getRVR());
        for(int i=0;i<10;i++)
        {
            simulator.clock();
            display("wysylam impusl");
            display("wartosc rejestru CVR "+simulator.getCVR());
            display("wartosc rejestru RVR"+simulator.getRVR());
        }
        display("ustawiam RVR na 3");
        simulator.setRVR(3);
        
        for(int i=0;i<6;i++)
        {
            simulator.clock();
            display("wysylam impusl");
            display("wartosc rejestru CVR "+simulator.getCVR());
            display("wartosc rejestru RVR"+simulator.getRVR());
        }
        
        
    }
    
    
}
