import java.util.Random;

public class MazeRobot extends GoofyRobot{
    private MotionState currentState;
    private Random rand;

    public MazeRobot(long idNumber){
        super(idNumber);
        currentState = MotionState.FORWARD;
        moveForward();
        rand = new Random(idNumber);
    }

    public void go(){
        while (getPowerLevel() > 0){
            int randNext = rand.nextInt(2);
            // replace this line with your code
        }
    }
}
