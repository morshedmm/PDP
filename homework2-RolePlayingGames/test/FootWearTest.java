
import static org.junit.Assert.assertEquals;

import battle.Dress;
import battle.FootWear;
import battle.HeadGear;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for FootWear class.
 * 
 */
public class FootWearTest {

  private Dress myFootWear1;
  private Dress myFootWear2;
  private Dress myHeadGear;
  
  /**
   * Sets up FootWear class testing with new foot wear objects. 
   */
  @Before
  public void setUp() {
    myFootWear1 = new FootWear("Better Shoe", 20, 10);
    myFootWear2 = new FootWear("Worse Sandal", 7, 12);
    myHeadGear = new HeadGear("Worse Hat", 7);
  }
  
  @Test
  public void testgetAttackeVal() {
    assertEquals(20, myFootWear1.getAttackVal());
  }
  
  @Test
  public void testgetName() {
    assertEquals("Better Shoe", myFootWear1.getName());
  }
  
  @Test
  public void testgetDefenseVal() {
    assertEquals(10, myFootWear1.getDefenseVal());
  }
  
  @Test
  public void testEqualsFootWear() {
    assertEquals(true, myFootWear1.equalsFootWear(myFootWear1));
  }
  
  @Test
  public void testEquals1() {
    assertEquals(true, myFootWear1.equals(myFootWear2));
  }
  
  @Test
  public void testEquals2() {
    assertEquals(false, myFootWear1.equals(myHeadGear));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAttackVal() {
    new FootWear("Worse Ring", -10, 10);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDefenseVal() {
    new FootWear("Worse Ring", 10, -10);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMergeDress() {
    myFootWear1.mergeDress(myHeadGear);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDressName() {
    new FootWear("", 10, 10);
  }
  
  @Test
  public void testMergeDress() {
    assertEquals(27, myFootWear1.mergeDress(myFootWear2).getAttackVal());
  }
  
  @Test
  public void testMergeDress1() {
    assertEquals(22, myFootWear1.mergeDress(myFootWear2).getDefenseVal());
  }
  
  @Test
  public void testMergeDress2() {
    assertEquals("Better, Worse Sandal", myFootWear1.mergeDress(myFootWear2).getName());
  }
  
  @Test
  public void testHashCode() {
    assertEquals(2100, myFootWear1.hashCode());
  }

}
