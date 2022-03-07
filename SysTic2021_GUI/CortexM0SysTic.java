import java.util.Observable;
/**
 * Write a description of class CortexM0SysTic here.
 *
 * @author (Adam Winiarski)
 * @version (a version number or a date)
 */

//extends Observable 

public class CortexM0SysTic extends Observable implements ICortexM0SysTic 
{
    // instance variables - replace the example below with your own
    private int registerCVR , registerRVR ;
    private boolean countFlag , tickIntFlag , enableFlag ;

    /**
     * Constructor for objects of class CortexM0SysTic
     */
    
    public CortexM0SysTic(int RVR, boolean enable)
    {
        // initialise instance variables
        this(0, RVR, enable) ;
        
    }
    public CortexM0SysTic(int CVR, int RVR, boolean enable)
    {
        // initialise instance variables
        registerCVR = CVR ;
        registerRVR = RVR ;
        enableFlag = enable ; 
        
    }
    public CortexM0SysTic()
    {
        this(0, 5, true) ;
    }

    
    
    
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setCVR(int newCVR)
    {
        registerCVR = 0 ;
        countFlag = false ;
    }
   
    public int getCVR()
    {
        return registerCVR ;
    }
    
    public void setRVR(int newRVR)
    {
        registerRVR = newRVR & 0xFFFFFF ;//wpisujemy tylko 24 bity
        
        
    }
    public int getRVR()
    {
        return registerRVR ;
    }
    
    public void setTickInt(boolean flag)
    {
        tickIntFlag = flag;
    }
    public boolean isTickIntFlag()
    {
        return tickIntFlag;
    }
    public void setEnableFlag( boolean flag)
    {
        this.enableFlag = flag;
    }
    public boolean isEnableFlag( )
    {
        return enableFlag;
    }
    public boolean inspectCountFlag()
    {
        return countFlag;
    }
    public boolean isCountFlag( )
    {
        boolean temp = this.countFlag;
        this.countFlag = false;
        return temp;
        
    }
    
    
    
    //absorbcja impusu
    
    public void clock()
    {
        if(!enableFlag)return;
        
        
        if(registerCVR == 0)
        {
            if(registerRVR == 0) {
                setEnableFlag(false);
                return;
            }
            registerCVR = registerRVR;
            ///this.countFlag = false;
            
            //przerwania
            //setChanged();
            //notifyObservers();
            
            
        }
        
        else
        {
            registerCVR -= 1;
            if(registerCVR == 0)
                {
                    this.countFlag = true;
                        if(tickIntFlag)
                        {
                            setChanged();
                            notifyObservers();
                        }
                }
            //else this.countFlag = false;
        }
    }
    @Override
    public String toString()
    {
        return "Jestem SysTick Model \n" + " RegisterCVR = " + registerCVR + 
                                    " Register RVR = " + registerRVR
                                    +" tick int  Flag = " + isTickIntFlag() + 
                                    " enable flag = " + isEnableFlag() +
                                    " countFlag =  " + isCountFlag();           
    }
}
