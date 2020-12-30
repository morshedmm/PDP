

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import mazes.Gold;
import mazes.GoldThief;
import mazes.Location;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Location class.
 * 
 */
public class LocationTest {
  
  private Location location;
  private Location location2;
  
  @Before
  public void setUp() {
    location = new Location(new Point(0, 1));
    location2 = new Location(new Point(2, 0), new GoldThief(10, 0.1));
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
    assertEquals(10, location2.getprize().getPrizeValue());
    assertEquals(0.1, location2.getprize().getFactor(), 1);
  }
  
  @Test
  public void testGetPrize2() {
    Location location3 = new Location(new Point(0, 1), new Gold(10));
    assertEquals(10, location3.getprize().getPrizeValue());
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
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPrizeInput() {
    new Location(new Point(0, 0), null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPointInput() {
    new Location(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidPrizeInput2() {
    new Location(null, new Gold(10));
  }
  
  @Test
  public void testToString() {
    assertEquals("0.0,1.0", location.toString());
  }

}
