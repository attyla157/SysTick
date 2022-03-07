import java.util.ArrayList;
import java.util.Iterator;
/**
 * Write a description of class ImpulseSource here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class ImpulseSource extends Thread implements IImpulseSource
{
    // instance variables - replace the example below with your own
    
     ImpulseListener odbiorca;
     ArrayList<ImpulseListener> odbiorcy = new ArrayList<>();
     private boolean  status;
     int periodMS,nCycles;
     GeneratorMode mode;
    
    
    public ImpulseSource(int initialPeriod)
    {
        periodMS = initialPeriod;
        mode = GeneratorMode.CONTINIOUS;
        nCycles = 1;
        
        start();
    }
    public ImpulseSource()
    {   
        this(1000);
    }
    public void run()
    {   int cyclesAlready = 0;
        while(!isInterrupted())
        {   if(status){
            Iterator<ImpulseListener> it = odbiorcy.iterator();
            
               switch(mode){
                case CONTINIOUS:
                    if(odbiorca != null) odbiorca.clock();
                     while(it.hasNext())
                     {
                         ImpulseListener il = it.next();
                         il.clock();
                       }
                    try
                    {
                    sleep(periodMS);
                
                     }catch(InterruptedException e){interrupt();}
                      break;
                case BURST:
                     if(odbiorca != null) odbiorca.clock();
                    
                     while(it.hasNext())
                     {
                         ImpulseListener il = it.next();
                         il.clock();
                       }
                    try
                    {
                    sleep(periodMS);
                    cyclesAlready++;
                    if (cyclesAlready == nCycles){
                        status = false;
                        cyclesAlready = 0;
                        break;
                    }
                
                     }catch(InterruptedException e){interrupt();}
                      break;
                    
                    }
            }  
        }
    }
    public void addImpulsListener(ImpulseListener il)
    {
       // odbiorca = il;
        odbiorcy.add(il);
        
    }
    public void removeImpulseListener(ImpulseListener il)
    {
        //odbiorca = null;
        odbiorcy.remove(il);
    }
    public void on()
    {
        status = true;
    }
    public void off()
    {
        status = false;
    }   
    public void setPeriod(int period)
    {
        periodMS = period;
        
    }
    
    public void setMode(GeneratorMode mode)
    {
        this.mode = mode;
    }
    public void setBurstCount(int n)
    {
        this.nCycles = n;
        
        
    }
}
