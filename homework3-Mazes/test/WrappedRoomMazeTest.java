


import mazes.WrappedRoomMaze;
import org.junit.Test;

/**
 * A JUnit test class for WrappedRoomMaze class.
 * 
 */
public class WrappedRoomMazeTest {

  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam() {
    new WrappedRoomMaze(4, 4, 34);
  }

}
