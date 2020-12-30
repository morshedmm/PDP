import static org.junit.Assert.assertEquals;

import conservatory.ShoreBird;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for ShoreBird class.
 */
public class ShoreBirdTest {
  
  private ShoreBird myBird1;
  
  @Before
  public void setUp() {
    myBird1 = new ShoreBird(7, 3, 2, "Asia", false,
      "Black", "Was sick while captured", "Auk", "insects 15", "freshwater", true);
  }
  
  @Test
  public void testGetId() {
    assertEquals(7, myBird1.getBirdId());
  }
  
  @Test
  public void testGetAge() {
    assertEquals(3, myBird1.getAge());
  }
  
  @Test
  public void tesGettNumOfWings() {
    assertEquals(2, myBird1.getNumOfWings());
  }
  
  @Test
  public void testGetInContinentsTheyFound() {
    assertEquals("Asia", myBird1.getInContinentsTheyFound());
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
    assertEquals("Auk", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("insects 15", myBird1.getFoodNeeded());
  }
  
  @Test
  public void testGetWaterBody() {
    assertEquals("freshwater", myBird1.getWaterBody());
  }
  
  @Test
  public void testGetEatFish() {
    assertEquals(true, myBird1.getEatFish());
  }
    
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new ShoreBird(-7, 3, 2, "Asia", false,
      "Black", "Was sick while captured", "Auk", "insects 15", "freshwater", true);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new ShoreBird(7, -3, 2, "Asia", false,
      "Black", "Was sick while captured", "Auk", "insects 15", "freshwater", true);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new ShoreBird(7, 3, -2, "Asia", false,
        "Black", "Was sick while captured", "Auk", "insects 15", "freshwater", true);
  }
}
