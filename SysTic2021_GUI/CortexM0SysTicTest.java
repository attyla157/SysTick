
import java.util.Observable;
import java.util.Observer;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CortexM0SysTicTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CortexM0SysTicTest implements Observer
{
    /**
     * 
     * Default constructor for test class CortexM0SysTicTest
     */
    boolean interuptHappened;
    public CortexM0SysTicTest()
    {
    }
    public void update(Observable subject, Object arg)
    {
        interuptHappened = true;
    }
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    @Test
    public void testIsCountFlag()
    {   
        CortexM0SysTic cortexM01 = new CortexM0SysTic(5, true);
        for(int i =0; i<8; i++)
            {
                cortexM01.clock();
            }
            assertEquals(true, cortexM01.isCountFlag());
            assertEquals(false, cortexM01.isCountFlag());
    }
    

    @Test
    public void testSetCVR()
    {
        CortexM0SysTic cortexM01 = new CortexM0SysTic(5, true);
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.setCVR(123);
        assertEquals(0, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
        
    }
    @Test
    public void testSetRVR()
    {
        CortexM0SysTic cortexM01 = new CortexM0SysTic(5, true);
        cortexM01.setRVR(10);
        assertEquals(10, cortexM01.getRVR());
        assertEquals(0, cortexM01.getCVR());
    }
    @Test
    public void testRVRzero()
    {
        CortexM0SysTic cortexM01 = new CortexM0SysTic(5, true);
        cortexM01.clock();
        cortexM01.setRVR(0);
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        cortexM01.clock();
        assertEquals(false, cortexM01.isEnableFlag());
    }
    @Test
    public void testInterrupt()
    {
        CortexM0SysTic cortexM01 = new CortexM0SysTic(5, true);
        cortexM01.addObserver(this);
        cortexM01.setTickInt(true);
        for(int i =0;i<6;i++)
            {
                cortexM01.clock();
            }
        assertEquals(true, interuptHappened);
        
    }
    @Test
    public void testInterruptOnCVRWrite()
    {
        CortexM0SysTic cortexM01 = new CortexM0SysTic(5, true);
        cortexM01.addObserver(this);
        cortexM01.setTickInt(true);
        cortexM01.setCVR(0);
        cortexM01.clock();
        assertEquals(false, interuptHappened);
        
    }
    
}


