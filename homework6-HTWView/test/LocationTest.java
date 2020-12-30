

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import mazes.CaveObject;
import mazes.Location;
import mazes.SuperBat;
import mazes.Wumpus;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Location class.
 * 
 */
public class LocationTest {
  
  private Location location;
  private Location location2;
  private Location location3;
  private CaveObject object1;
  
  
  /**
   * Sets up the test.
   */
  @Before
  public void setUp() {
    object1 = new Wumpus();
    CaveObject object2 = new SuperBat();
    location = new Location(new Point(0, 1));
    location2 = new Location(new Point(2, 0), object1);
    location3 = new Location(new Point(0, 1), object1, object2);
  }

  @Test
  public void testLocationX() {
    assertEquals(0.0, location.getLocationCoord().getX(), 1);
  }
  
  @Test
  public void testLocationY() {
    assertEquals(1.0, location.getLocationCoord().getX(), 1);
  }
  
  @Test
  public void testGetPrize() {
    assertEquals("You are eaten by Wumpus", location2.getprize().get(1).getMessage());
  }
  
  @Test
  public void testGetPrize2() {
    assertEquals("1", location3.getprize().get(1).getMessage());
  }
  
  @Test
  public void testEquals() {
    Location location3 = new Location(new Point(0, 1));
    assertEquals(true, location.equals(location3));
  }
  
  @Test
  public void testEquals2() {
    Location location3 = new Location(new Point(0, 0));
    assertEquals(false, location.equals(location3));
  }
  
  @Test
  public void testAddObjects() {
    Location location3 = new Location(new Point(0, 0));
    location3.addObjects(location2.getprize());
    assertEquals("You are eaten by Wumpus", location3.getprize().get(1).getMessage());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPrizeInput() {
    new Location(new Point(0, 0), null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPointInput() {
    new Location(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPrizeInput1() {
    new Location(new Point(0, 0), null, object1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPrizeInput2() {
    new Location(new Point(0, 0), object1, null);
  }
  
  @Test
  public void testToString() {
    assertEquals("0.0,1.0", location.toString());
  }
  
  @Test
  public void testHashCode() {
    assertEquals(1, location.hashCode());
  }

}
