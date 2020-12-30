

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import mazes.CaveObject;
import mazes.Wumpus;

/**
 * A JUnit test class for Wumpus.
 * 
 */
public class WumpusTest {
  
  private CaveObject object1;
  
  @Before
  public void setUp() {
    object1 = new Wumpus();
    
  }

  @Test
  public void testMessage() {
    assertEquals("You are eaten by Wumpus", object1.getMessage());
  }
  
  @Test
  public void testEqualsWumpus() {
    assertEquals(true, object1.equalsWumpus());
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
