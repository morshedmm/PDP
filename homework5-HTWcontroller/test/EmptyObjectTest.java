

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import mazes.CaveObject;
import mazes.EmptyObject;

/**
 * A JUnit test class for EmptyObject.
 * 
 */
public class EmptyObjectTest {

  private CaveObject object1;
  
  @Before
  public void setUp() {
    object1 = new EmptyObject();
    
  }

  @Test
  public void testMessage() {
    assertEquals("", object1.getMessage());
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
    assertEquals(false, object1.equalsSuperBat());
  }

}
