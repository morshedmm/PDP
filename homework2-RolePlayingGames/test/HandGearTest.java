

import static org.junit.Assert.assertEquals;

import battle.Dress;
import battle.HandGear;
import battle.HeadGear;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for HandGear class.
 * 
 */
public class HandGearTest {
  
  private Dress myHandGear1;
  private Dress myHandGear2;
  private Dress myHeadGear;
  
  /**
   * Sets up HandGear class testing with new hand gear objects.
   */
  @Before
  public void setUp() {
    myHandGear1 = new HandGear("Better Gloves", 10);
    myHandGear2 = new HandGear("Worse Ring", 7);
    myHeadGear = new HeadGear("Worse Hat", 7);
  }
  
  @Test
  public void testgetAttackeVal() {
    assertEquals(10, myHandGear1.getAttackVal());
  }
  
  @Test
  public void testgetName() {
    assertEquals("Better Gloves", myHandGear1.getName());
  }
  
  @Test
  public void testEqualsHandGear() {
    assertEquals(true, myHandGear1.equalsHandGear(myHandGear1));
  }
  
  @Test
  public void testEquals1() {
    assertEquals(true, myHandGear1.equals(myHandGear2));
  }
  
  @Test
  public void testEquals2() {
    assertEquals(false, myHandGear1.equals(myHeadGear));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAttackVal() {
    new HandGear("Worse Ring", -10);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMergeDress() {
    myHandGear1.mergeDress(myHeadGear);
  }
  
  @Test
  public void testMergeDress() {
    assertEquals(17, myHandGear1.mergeDress(myHandGear2).getAttackVal());
  }
  
  @Test
  public void testMergeDress2() {
    assertEquals("Better, Worse Ring", myHandGear1.mergeDress(myHandGear2).getName());
  }

}
