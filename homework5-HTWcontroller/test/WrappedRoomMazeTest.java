


import mazes.WrappedRoomMaze;
import org.junit.Test;

/**
 * A JUnit test class for WrappedRoomMaze class.
 * 
 */
public class WrappedRoomMazeTest {

  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam() {
    new WrappedRoomMaze(0, 3, 1, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam2() {
    new WrappedRoomMaze(3, 0, 1, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam3() {
    new WrappedRoomMaze(3, 3, -1, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam4() {
    new WrappedRoomMaze(3, 3, 1, -1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam5() {
    new WrappedRoomMaze(3, 3, 1, 1, -1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam6() {
    new WrappedRoomMaze(3, 3, 15, 1, 1);
  }

}
