

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import mazes.EmptyObject;
import mazes.Location;
import mazes.PerfectMaze;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for PerfectMaze class.
 * 
 */
public class PerfectMazeTest {
  
  private PerfectMaze myPerfectMaze;
  private Location location;
  
  @Before
  public void setUp() {
    myPerfectMaze = new PerfectMaze(3, 3, 3, 1);
    location = new Location(new Point(0, 1), new EmptyObject());
  }
  
  @Test
  public void testLocationInfo() {
    assertEquals("0.0,1.0Possible moves are: SouthEast", myPerfectMaze.giveLocationInfo(location));
  }
  
  @Test
  public void testToString() {
    assertEquals("8", new PerfectMaze(3, 3, 3, 1).toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam() {
    new PerfectMaze(0, 3, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam2() {
    new PerfectMaze(3, 0, 1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam3() {
    new PerfectMaze(3, 3, -1, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam4() {
    new PerfectMaze(3, 3, 1, -1);
  }

}
