

import mazes.RoomMaze;
import org.junit.Test;


/**
 * A JUnit test class for RoomMaze class.
 * 
 */
public class RoomMazeTest {
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam() {
    new RoomMaze(0, 0, 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam2() {
    new RoomMaze(-1, 4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam3() {
    new RoomMaze(4, -1, 1);
  } 
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam4() {
    new RoomMaze(4, 4, 15);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam5() {
    new RoomMaze(4, 4, -2);
  }

}
