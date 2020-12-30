

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import mazes.CaveObject;
import mazes.SuperBat;

/**
 * A Junit test class for SuperBat class.
 * 
 */
public class SuperBatTest {

  private CaveObject object1;
  
  @Before
  public void setUp() {
    object1 = new SuperBat();
    
  }

  @Test
  public void testMessage() {
    assertEquals("1", object1.getMessage());
  }
  
  @Test
  public void testEqualsWumpus() {
    assertEquals(false, object1.equalsWumpus());
  }
  
  @Test
  public void testEqualsPit() {
    assertEquals(false, object1.equalsBottomlessPit());
  }
  
  @Test
  public void testEqualsSuperBat() {
    assertEquals(true, object1.equalsSuperBat());
  }

}
