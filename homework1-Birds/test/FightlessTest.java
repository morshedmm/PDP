

import static org.junit.Assert.assertEquals;

import conservatory.Fightless;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Fightless class.
 */
public class FightlessTest {
  
  private Fightless myBird1;
  
  @Before
  public void setUp() {
    myBird1 = new Fightless(2, 1, 2, true, false,
      "White", "Was not sick while captured", "Swans", "insects 5");
  }
  
  @Test
  public void testGetId() {
    assertEquals(2, myBird1.getBirdId());
  }
  
  @Test
  public void testGetAge() {
    assertEquals(1, myBird1.getAge());
  }
  
  @Test
  public void testGetNumOfWings() {
    assertEquals(2, myBird1.getNumOfWings());
  }
  
  @Test
  public void testGetIfHidesDuringDay() {
    assertEquals(true, myBird1.getIfHidesDuringDay());
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
    assertEquals("Was not sick while captured", myBird1.getDescription());
  }
  
  @Test
  public void testGetType() {
    assertEquals("Swans", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("insects 5", myBird1.getFoodNeeded());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new Fightless(-2, 1, 2, true, false,
      "White", "Was not sick while captured", "Swans", "insects 5");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new Fightless(2, -1, 2, true, false,
      "White", "Was not sick while captured", "Swans", "insects 5");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new Fightless(2, 1, -2, true, false,
      "White", "Was not sick while captured", "Swans", "insects 5");
  }
  
  @Test
  public void testToString() {
    assertEquals("class conservatory.Fightless", myBird1.toString());
  }
}
