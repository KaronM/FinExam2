import java.util.Calendar;

/**
 * This class is a template for objects that provide a timing mechanism
 * within client software.
 * 
 * @author Marwan Rasamny 
 * @version 0.1
 */
public class StopWatch
{
    private static final int RESET = 0;
    private static final int START = 1;
    private static final int STOP = 2;
    
    private long accumulatedTime;
    private long initialTime;
    private long currentTime;
    private int currentState;
    
    /**
     * This constructor places new instances in reset mode.
     */
    public StopWatch()
    {
        reset();
    }
    
    /**
     * Resets this stopwatch object.
     */
    public void reset()
    {
        currentState = RESET;
        initialTime = 0;
        currentTime = 0;
        accumulatedTime = 0;
    }
    
    /**
     * Starts the stopwatch counting
     * to first invocation on subsequent invocations of this method.
     */
    public void start()
    {
        if (currentState == RESET)
        {
            currentTime = Calendar.getInstance().getTimeInMillis();  // cannot factor out because of START state
            initialTime = currentTime;
            accumulatedTime = 0;
        } else if (currentState == STOP)
        {
            currentTime = Calendar.getInstance().getTimeInMillis();
            initialTime = currentTime;
        }
        currentState = START;
    }
   
    public void stop()
    {
        if (currentState == START)
        {
            currentTime = Calendar.getInstance().getTimeInMillis();
            accumulatedTime += (currentTime-initialTime);
            currentState = STOP;
        }
    }
    
    /**
     * Returns the elapsted time the clock was running (ie not stopped) in seconds.
     * @return returns a long integer representing elapsed time the clock was running (ie not stopped) in seconds.
     */
    public double elapsedTime()
    {
        if (currentState == START)
        {
            stop();
            start();
        }
        return accumulatedTime/1000.0;
    }
    
    /**
     * Returns a string representation of this stopwatch.
     * @return Returns a string representing the elapsed time.
     */
    public String toString()
    {
        return elapsedTime()+" s";
    }
}
