import java.util.Random;
import java.util.List;
import java.util.LinkedList;

/**
 * This class connects a software object with a physical robot connected with four 
 * bumper sensors.
 * 
 * @author Donald Duck 
 * @version 1.0
 */
public class GoofyRobot
{
    private final static int STOPPED = 0;
    private final static int MOVING_FORWARD = 1;
    private final static int MOVING_RIGHT = 2;
    private final static int MOVING_BACKWARD = 3;
    private final static int MOVING_LEFT = 4;
    private final static int MAX_POWER_LEVEL = 100;
    private final static double MAX_POWER_LEVEL_DISCHARGE_RATE = 100/2.0; // in levels/sec
    private final static long SLEEP_TIME = (long)((100/MAX_POWER_LEVEL_DISCHARGE_RATE)/15*1000);
    private int currentState;
    private StopWatch timer;
    private double startingPowerLevel;
    private double powerLevel; // measured from 0 to 100; with 0 no power left
    private Random rand;
    private long idNumber;
    private List<String> path;
    /**
     * Initializes and connects this robot to the physical robot with ID number idNumber
     * @param idNumber ID number of the physical robot
     */
    public GoofyRobot(long idNumber)
    {
        init(idNumber, MAX_POWER_LEVEL);
    }
    
    /**
     * Initializes and connects this robot to the physical robot with ID number idNumber
     * @param idNumber ID number of the physical robot
     * @param powerLevel starting power level of the physical robot
     */
    public GoofyRobot(long idNumber, int powerLevel)
    {
        init(idNumber, powerLevel);
    }
    
    private void init(long idNumber, int powerLevel)
    {
        timer = new StopWatch();
        timer.reset();
        this.powerLevel = powerLevel;
        startingPowerLevel = powerLevel;
        this.idNumber = idNumber;
        currentState = STOPPED;
        rand = new Random(17);
        path = new LinkedList<String>();
    }
    
    /**
     * Stops the robot if it is moving.
     */
    public void stop()
    {
        timer.stop();
        path.add("stop");
        currentState = STOPPED;
   }
    
    /**
     * Moves the robot to the left indefinitely until a stop() or another command is issued.
     */
    public void moveLeft()
    {
        if (getPowerLevel() > 0)
        {
            path.add("Move Left");
            timer.start();
            currentState = MOVING_LEFT;
            try{
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Moves the robot to the right indefinitely until a stop() or another command is issued.
     */
    public void moveRight()
    {
        if (getPowerLevel() > 0)
        {
            path.add("Move Right");
            timer.start();
            currentState = MOVING_RIGHT;
            try{
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Moves the robot forward indefinitely until a stop() or another command is issued.
     */
    public void moveForward()
    {
        if (getPowerLevel() > 0)
        {
            path.add("Move Forward");
            timer.start();
            currentState = MOVING_FORWARD;
            try{
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Moves the robot backward indefinitely until a stop() or another command is issued.
     */
    public void moveBackward()
    {
        if (getPowerLevel() > 0)
        {
            path.add("Move Backward");
            timer.start();
            currentState = MOVING_BACKWARD;
            try{
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Checks and returns the powerLevel of this robot.
     * @return power level of this robot between 0 and 100, inclusively.
     */
    public double getPowerLevel()
    {
        if (powerLevel == 0)
        {
            return 0;
        }
        
        powerLevel = startingPowerLevel-(MAX_POWER_LEVEL_DISCHARGE_RATE*timer.elapsedTime());
        if (powerLevel <= 0)
        {
            stop();
            powerLevel = 0;
            timer.reset();
            startingPowerLevel=0;
        }
        return powerLevel;
    }
    
    /**
     * Returns a true if the button sensor has been pushed; otherwise, returns false
     * 
     * @param buttonNumber Any of the four buttons (0 corresponds to B0, 1 to B1, etc..) on the robot.
     */
    public boolean isPressed(int buttonNumber)
    {
        if (currentState == STOPPED ||
                buttonNumber == 0 && currentState != MOVING_FORWARD ||
            buttonNumber == 1 && currentState != MOVING_RIGHT ||
            buttonNumber == 2 && currentState != MOVING_BACKWARD ||
            buttonNumber == 3 && currentState != MOVING_LEFT)
        {
            return false;
        }
        return rand.nextDouble() <= .10;
    }
    
    /**
     * Returns a string representation of this robot.  That is, it returns a string of all the moves 
     * or commands that have been issued on the robot.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (String move : path)
        {
            sb.append(move);
            sb.append('\n');
        }
        return sb.toString();
    }
}
