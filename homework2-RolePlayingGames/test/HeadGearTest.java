

import static org.junit.Assert.assertEquals;

import battle.Dress;
import battle.HandGear;
import battle.HeadGear;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for HeadGear class. 
 *
 */
public class HeadGearTest {
  
  private Dress myHeadGear1;
  private Dress myHeadGear2;
  private Dress myHandGear;
  
  /**
   * Sets up HeadGear class testing with new head gear objects.
   */
  @Before
  public void setUp() {
    myHeadGear1 = new HeadGear("Better Cap", 10);
    myHeadGear2 = new HeadGear("Worse Hat", 7);
    myHandGear = new HandGear("Worse Hat", 7);
  }

  @Test
  public void testgetDefenseVal() {
    assertEquals(10, myHeadGear1.getDefenseVal());
  }
  
  @Test
  public void testgetName() {
    assertEquals("Better Cap", myHeadGear1.getName());
  }
  
  @Test
  public void testEqualsHeadGear() {
    assertEquals(true, myHeadGear1.equalsHeadGear(myHeadGear1));
  }
  
  @Test
  public void testEquals1() {
    assertEquals(true, myHeadGear1.equals(myHeadGear2));
  }
  
  @Test
  public void testEquals2() {
    assertEquals(false, myHeadGear1.equals(myHandGear));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDefenseVal() {
    new HeadGear("Worst Cap", -10);
  }
  
  @Test
  public void testHashCode() {
    assertEquals(100, myHeadGear1.hashCode());
  }

}
