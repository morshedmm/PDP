

import static org.junit.Assert.assertEquals;

import conservatory.Owl;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Owl class.
 */
public class OwlTest {
  
  private Owl myBird1;
  
  @Before
  public void setUp() {
    myBird1 = new Owl(1, 1, 2, 1, false,
      "White", "Gentle", "AsianOwl", "insects 15 fish 1");    
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
  public void testGetDiscLen() {
    assertEquals(1, myBird1.getDiscLen());
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
    assertEquals("Gentle", myBird1.getDescription());
  }
  
  @Test
  public void testGetType() {
    assertEquals("AsianOwl", myBird1.getType());
  }
  
  @Test
  public void testGetFoodNeeded() {
    assertEquals("insects 15 fish 1", myBird1.getFoodNeeded());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    myBird1 = new Owl(-1, 1, 2, 1, false,
      "White", "Gentle", "AsianeOwl", "insects 15 fish 1"); 
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAge() {
    myBird1 = new Owl(1, -1, 2, 1, false,
      "White", "Gentle", "AsianeOwl", "insects 15 fish 1"); 
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfWings() {
    myBird1 = new Owl(1, 1, -2, 1, false,
      "White", "Gentle", "AsianeOwl", "insects 15 fish 1"); 
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDiscLen() {
    myBird1 = new Owl(1, 1, 2, -1, false,
      "White", "Gentle", "AsianeOwl", "insects 15 fish 1"); 
  }

}
