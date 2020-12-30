import static org.junit.Assert.assertEquals;

import conservatory.WaterFowl;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for WaterFowl class.
 */
public class WaterFowlTest {

  private WaterFowl myBird1;
  
  @Before
  public void setUp() {
    myBird1 = new WaterFowl(7, 3, 2, 2, false,
      "Black", "Was sick while captured", "Duck", "insects 15", "salt", true);
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
  public void testGetSwimmingAge() {
    assertEquals(2, myBird1.getSwimmingAge());
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
    assertEquals("Duck", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("insects 15", myBird1.getFoodNeeded());
  }
  
  @Test
  public void testGetWaterBody() {
    assertEquals("salt", myBird1.getWaterBody());
  }
  
  @Test
  public void testGetEatFish() {
    assertEquals(true, myBird1.getEatFish());
  }
    
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new WaterFowl(-7, 3, 2, 2, false,
      "Black", "Was sick while captured", "Auk", "insects 15", "salt", true);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new WaterFowl(7, -3, 2, 2, false,
      "Black", "Was sick while captured", "Auk", "insects 15", "salt", true);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new WaterFowl(7, 3, -2, 2, false,
        "Black", "Was sick while captured", "Auk", "insects 15", "salt", true);
  }

}
