

import mazes.RoomMaze;
import org.junit.Test;


/**
 * A JUnit test class for RoomMaze class.
 * 
 */
public class RoomMazeTest {
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam() {
    new RoomMaze(0, 3, 1, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam2() {
    new RoomMaze(3, 0, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam3() {
    new RoomMaze(3, 3, -1, 1, 1);
  } 
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam4() {
    new RoomMaze(3, 3, 1, -1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam5() {
    new RoomMaze(3, 3, 1, 1, -1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam6() {
    new RoomMaze(3, 3, 5, 1, 1);
  }

}
