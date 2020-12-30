import static org.junit.Assert.assertEquals;

import conservatory.Prey;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for Prey class.
 */
public class PreyTest {
  
  private Prey myBird1;

  @Before
  public void setUp() {
    myBird1 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
      "Black", "Was sick while captured", "Eagles", "fruit 2");
  }
  
  @Test
  public void testGetId() {
    assertEquals(1, myBird1.getBirdId());
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
  public void testGetActivity() {
    assertEquals("Very Dangerous for other birds", myBird1.getActivity());
  }
  
  @Test
  public void testGetWhatIsCaptured() {
    assertEquals("Captures other birds", myBird1.getWhatIsCaptured());
  }
  
  @Test
  public void testGetIsEtinct() {
    assertEquals(false, myBird1.getIsExtinct());
  }
  
  @Test
  public void testGetColor() {
    assertEquals("Black", myBird1.getBirdColor());
  }
  
  @Test
  public void testGetDescription() {
    assertEquals("Was sick while captured", myBird1.getDescription());
  }
  
  @Test
  public void testGetType() {
    assertEquals("Eagles", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("fruit 2", myBird1.getFoodNeeded());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new Prey(-1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
      "Black", "Was sick while captured", "Eagles", "fruit 2");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new Prey(1, -1, 2, "Very Dangerous for other birds", "Captures other birds", false,
      "Black", "Was sick while captured", "Eagles", "fruit 2");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new Prey(1, 1, -2, "Very Dangerous for other birds", "Captures other birds", false,
      "Black", "Was sick while captured", "Eagles", "fruit 2");
  }
  
  @Test
  public void testToString() {
    assertEquals("class conservatory.Prey", myBird1.toString());
  }

}
