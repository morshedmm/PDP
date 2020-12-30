

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import mazes.GoldThief;
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
    myPerfectMaze = new PerfectMaze(4, 3);
    location = new Location(new Point(0, 1), new GoldThief(10, 0.1));
  }
  
  @Test
  public void testLocationInfo() {
    assertEquals("0.0,1.0Possible moves are: SouthEast", myPerfectMaze.giveLocationInfo(location));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam() {
    new PerfectMaze(0, 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam2() {
    new PerfectMaze(0, -1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfIllegalMazeParam3() {
    new PerfectMaze(-1, 0);
  }

}
