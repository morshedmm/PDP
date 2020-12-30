

import static org.junit.Assert.assertEquals;

import conservatory.Aviary;
import conservatory.Bird;
import conservatory.Fightless;
import conservatory.Prey;
import conservatory.WaterFowl;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for Aviary class.
 */
public class AviaryTest {
  
  private Aviary aviary1;
  private Bird bird1;
  private Bird bird2;
  private Bird bird3;
  private Bird bird4;
  private Bird bird5;
  private Bird bird6;

  /**
   * Used to setup aviary and bird objects for using in testing.
   */
  @Before
  public void setUp() {
    aviary1 = new Aviary(1, 1, 1, 5);
    bird1 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
      "Black", "Was sick while captured", "Eagles", "fruit 2");
    bird2 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
        "Black", "Was sick while captured", "Eagles", "fruit");    
    bird3 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
        "Black", "Was sick while captured", "Eagles", "fr 5");
    bird4 = new Fightless(2, 1, 2, true, false,
      "White", "Was not sick while captured", "swans", "insects 5");
    bird5 = new WaterFowl(3, 3, 2, 1, false,
      "Brown", "Was not sick while captured", "swans", "insects 5 fish 3", "salt", true);
    bird6 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
        "Black", "Was sick while captured", "Eagles", "fruit 3");
  }

  @Test
  public void testGetId() {
    assertEquals(1, aviary1.getId());
  }
  
  @Test
  public void testGetLocationX() {
    assertEquals(1, aviary1.getLocationX());
  }  
  
  @Test
  public void testGetLocationY() {
    assertEquals(1, aviary1.getLocationY());
  } 
  
  @Test
  public void testGetMaxNumOfBirds() {
    assertEquals(5, aviary1.getMaxNumOfBirds());
  }
  
  @Test
  public void testAssignBird() {
    assertEquals(true, aviary1.assignBird(bird1));
  }  
  
  /*
   * Tests with invalid food requirement. 
   */
  @Test
  public void testAssignBird2() {
    assertEquals(false, aviary1.assignBird(bird2));
  }  
  
  @Test
  public void testGetBirdList() {
    aviary1.assignBird(bird1);
    assertEquals(bird1, aviary1.getBirdsList().get(0));
  }
  
  @Test
  public void testAssignBird3() {
    aviary1.assignBird(bird1);
    assertEquals(false, aviary1.assignBird(bird4));
  }  
  
  @Test
  public void testAssignBird4() {
    aviary1.assignBird(bird4);
    assertEquals(false, aviary1.assignBird(bird1));
  }  
  
  @Test
  public void testAssignBird5() {
    aviary1.assignBird(bird4);
    assertEquals(false, aviary1.assignBird(bird5));
  }
  
  @Test
  public void testAssignBird6() {
    aviary1.assignBird(bird5);
    assertEquals(false, aviary1.assignBird(bird4));
  }
  
  @Test
  public void testAssignBird7() {
    aviary1.assignBird(bird5);
    assertEquals(false, aviary1.assignBird(bird1));
  }
  
  @Test
  public void testAssignBird8() {
    aviary1.assignBird(bird1);
    assertEquals(false, aviary1.assignBird(bird5));
  }
  
  @Test
  public void testAssignBird9() {
    aviary1.assignBird(bird1);
    aviary1.assignBird(bird6);
    aviary1.assignBird(bird1);
    aviary1.assignBird(bird6);
    aviary1.assignBird(bird1);
    assertEquals(false, aviary1.assignBird(bird6));
  }
  
  @Test
  public void testGetFoodQuantity() {
    aviary1.assignBird(bird1);
    aviary1.assignBird(bird6);
    assertEquals(5, (int) aviary1.getFoodQuantity().get("fruit"));   
  } 
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidId() {
    aviary1 = new Aviary(-1, 1, 1, 5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidLocationX() {
    aviary1 = new Aviary(1, -1, 1, 5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidLocationY() {
    aviary1 = new Aviary(1, 1, -1, 5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMaxNumOfBirds() {
    aviary1 = new Aviary(1, 1, 1, -5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidFoodName() {
    aviary1.assignBird(bird3);
  }
}
