

import static org.junit.Assert.assertEquals;

import conservatory.Parrot;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Parrot class.
 */
public class ParrotTest {
  
  private Parrot myBird1;
  
  @Before
  public void setUp() {
    myBird1 = new Parrot(2, 1, 2, 10, false,
      "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
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
  public void testGetWordCount() {
    assertEquals(10, myBird1.getWordCount());
  }
    
  @Test
  public void testGetIsEtinct() {
    assertEquals(false, myBird1.getIsExtinct());
  }
  
  @Test
  public void testGetColor() {
    assertEquals("Green", myBird1.getBirdColor());
  }
  
  @Test
  public void testGetDescription() {
    assertEquals("Was not sick while captured", myBird1.getDescription());
  }
  
  @Test
  public void testGetType() {
    assertEquals("Duck", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("insects 5 fish 3", myBird1.getFoodNeeded());
  }
  
  @Test
  public void testGetFavWords() {
    assertEquals("Hi", myBird1.getFavWords());
  }  
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new Parrot(-2, 1, 2, 10, false,
      "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new Parrot(2, -1, 2, 10, false,
      "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new Parrot(2, 1, -2, 10, false,
      "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidWordCount() {
    myBird1 = new Parrot(2, 1, 2, -10, false,
      "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
  }

}
