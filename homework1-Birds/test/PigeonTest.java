

import static org.junit.Assert.assertEquals;

import conservatory.Pigeon;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for Pigeon class.
 */
public class PigeonTest {
  
  private Pigeon myBird1;

  @Before
  public void setUp() {
    myBird1 = new Pigeon(1, 2, 2, false, 1,
      "White", "Very active during day", "Dove", "nuts 8");
  } 
  
  @Test
  public void testGetId() {
    assertEquals(1, myBird1.getBirdId());
  }
  
  @Test
  public void testGetAge() {
    assertEquals(2, myBird1.getAge());
  }
  
  @Test
  public void testGetNumOfWings() {
    assertEquals(2, myBird1.getNumOfWings());
  }
  
  @Test
  public void testGetMilkFeedingAge() {
    assertEquals(1, myBird1.getMilkFeedingAge());
  }
    
  @Test
  public void testGetIsEtinct() {
    assertEquals(false, myBird1.getIsExtinct());
  }
  
  @Test
  public void testGetColor() {
    assertEquals("White", myBird1.getBirdColor());
  }
  
  @Test
  public void testGetDescription() {
    assertEquals("Very active during day", myBird1.getDescription());
  }
  
  @Test
  public void testGetType() {
    assertEquals("Dove", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("nuts 8", myBird1.getFoodNeeded());
  }
    
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new Pigeon(-1, 2, 2, false, 1,
      "White", "Very active during day", "Dove", "nuts 8");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new Pigeon(1, -2, 2, false, 1,
      "White", "Very active during day", "Dove", "nuts 8");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new Pigeon(1, 2, -2, false, 1,
      "White", "Very active during day", "Dove", "nuts 8");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMilkFeedingAge() {
    myBird1 = new Pigeon(1, 2, 2, false, -1,
      "White", "Very active during day", "Dove", "nuts 8");
  }
  

}
