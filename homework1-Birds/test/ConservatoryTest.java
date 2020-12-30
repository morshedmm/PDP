import static org.junit.Assert.assertEquals;

import conservatory.Bird;
import conservatory.Conservatory;
import conservatory.Prey;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Prey class.
 */
public class ConservatoryTest {

  private Bird bird1;
  private Bird bird2;

  /**
   * Used to set up aviary and bird objects before testing.
   */
  @Before
  public void setUp() {
    bird1 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", false,
      "Black", "Was sick while captured", "Eagles", "fruit 2");
    bird2 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", true,
        "Black", "Was sick while captured", "Eagles", "fruit 2");
    
  }
  
  @Test
  public void testAddAviary() {
    assertEquals(1, Conservatory.addAviary(1, 1, 1).getId());
  }
  
  @Test
  public void testAddAviary2() {
    assertEquals(2, Conservatory.addAviary(2, 2, 2).getLocationX());
  }  
  
  @Test
  public void testAddAviary3() {
    assertEquals(3, Conservatory.addAviary(3, 3, 3).getLocationY());
  }  
  
  @Test
  public void testAddAviary4() {
    assertEquals(5, Conservatory.addAviary(4, 4, 4).getMaxNumOfBirds());
  }  
  
  @Test
  public void testAddAviary5() {
    Conservatory.addAviary(1, 1, 1);
    assertEquals(null, Conservatory.addAviary(1, 1, 1));
  }  
  
  @Test
  public void testAddAviary6() {
    Conservatory.addAviary(1, 1, 1);
    assertEquals(null, Conservatory.addAviary(1, 2, 2));
  }  
  
  @Test
  public void testAddBird1() {
    assertEquals("No aviary has room to take this bird!!", Conservatory.addBird(bird1));
  }
  
  @Test
  public void testAddBird2() {
    assertEquals("This bird can not be kept in this conservatory as it is extinct", 
        Conservatory.addBird(bird2));
  }  
  
  @Test
  public void testAddBird3() {
    Conservatory.addAviary(6, 6, 6);
    assertEquals("Bird 1 was added to Aviary 6", Conservatory.addBird(bird1));  
  }

  @Test
  public void testLocateBird() {
    Conservatory.addBird(bird1);
    assertEquals("Bird 1 lives in Aviary 6", Conservatory.locateBird(1));  
  }
  
  @Test
  public void testLocateBird2() {
    assertEquals("Bird 10 was not found in this conservatory", Conservatory.locateBird(10));  
  }
  
}
