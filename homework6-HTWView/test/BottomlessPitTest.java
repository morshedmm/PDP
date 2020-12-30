

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import mazes.BottomlessPit;
import mazes.CaveObject;

/**
 * A JUnit test class for BottomlessPit class.
 * 
 */
public class BottomlessPitTest {

  private CaveObject object1;
  
  @Before
  public void setUp() {
    object1 = new BottomlessPit();
    
  }

  @Test
  public void testMessage() {
    assertEquals("You have fallen in bottomless cave", object1.getMessage());
  }
  
  @Test
  public void testEqualsWumpus() {
    assertEquals(false, object1.equalsWumpus());
  }
  
  @Test
  public void testEqualsPit() {
    assertEquals(true, object1.equalsBottomlessPit());
  }
  
  @Test
  public void testEqualsSuperBat() {
    assertEquals(false, object1.equalsSuperBat());
  }

}
