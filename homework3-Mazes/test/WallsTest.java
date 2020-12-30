

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import mazes.Location;
import mazes.Walls;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for Walls class.
 * 
 */
public class WallsTest {
  
  private Walls wall;
  private Location location1;
  private Location location2;
  
  /**
   * Sets up the testing.
   */
  @Before
  public void setUp() {
    location1 = new Location(new Point(0, 0));
    location2 = new Location(new Point(0, 1));
    wall = new Walls(location1, location2);
  }

  @Test
  public void testGetLocation1() {
    assertEquals(location1, wall.getLocation1());
  }
  
  @Test
  public void testGetLocation2() {
    assertEquals(location2, wall.getLocation2());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidLocationInput() {
    new Walls(null, location2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidLocationInput2() {
    new Walls(location2, null);
  }

}
