import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeRobotTest {

    @Test
    @DisplayName("[1] test go_1")
    void go_1() {
        MazeRobot robot = new MazeRobot(12345);
        robot.go();
        String expected = "Move Forward\n" +
                "Move Right\n" +
                "Move Backward\n" +
                "Move Right\n" +
                "Move Backward\n" +
                "Move Right\n" +
                "Move Forward\n" +
                "Move Left\n" +
                "Move Forward\n" +
                "Move Right\n" +
                "Move Backward\n" +
                "Move Left\n" +
                "stop\n";
        assertEquals(expected,robot.toString());
    }

    @Test
    @DisplayName("[1] test go_2")
    void go_2() {
        MazeRobot robot = new MazeRobot(21);
        robot.go();
        String expected = "Move Forward\n" +
                "Move Left\n" +
                "Move Forward\n" +
                "Move Right\n" +
                "Move Forward\n" +
                "Move Left\n" +
                "Move Backward\n" +
                "Move Left\n" +
                "Move Backward\n" +
                "Move Right\n" +
                "Move Backward\n" +
                "Move Right\n" +
                "stop\n";
        assertEquals(expected,robot.toString());
    }

    @Test
    @DisplayName("[1] test go_3")
    void go_3() {
        MazeRobot robot = new MazeRobot(8734);
        robot.go();
        String expected = "Move Forward\n" +
                "Move Right\n" +
                "Move Forward\n" +
                "Move Left\n" +
                "Move Backward\n" +
                "Move Right\n" +
                "Move Forward\n" +
                "Move Left\n" +
                "Move Backward\n" +
                "Move Right\n" +
                "Move Backward\n" +
                "Move Left\n" +
                "stop\n";
        assertEquals(expected,robot.toString());
    }
}