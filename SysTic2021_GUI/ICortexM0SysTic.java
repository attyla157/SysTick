
/**
 * Write a description of interface ICortexM0SysTic here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface ICortexM0SysTic extends ImpulseListener
{
    public void setCVR(int newCVR);
   
    public int getCVR();
    
    public void setRVR(int newRVR);
    
    public int getRVR();
    
    
    public void setTickInt(boolean flag);
    
    public boolean isTickIntFlag();
    
    public void setEnableFlag( boolean flag);
    
    
    
    
    //absorbcja impusu
    
    public void clock();
   
}
