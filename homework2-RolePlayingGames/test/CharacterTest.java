

import static org.junit.Assert.assertEquals;

import battle.Character;
import battle.Dress;
import battle.FootWear;
import battle.HandGear;
import battle.HeadGear;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for Character class.
 */
public class CharacterTest {
  
  private Character character1;
  private Character character2;
  private Dress myFootWear1;
  private Dress myFootWear2;
  private Dress myHeadGear;
  private Dress myHandGear1;
  private Dress myHandGear2;
  
  /**
   * Sets up the character tests with new characters and dresses.
   */
  @Before
  public void setUp() {
    character1 = new Character("Batman", 500, 400);
    character2 = new Character("Spiderman", 5, 4);
    myFootWear1 = new FootWear("Better Shoe", 20, 10);
    myFootWear2 = new FootWear("Worse Sandal", 7, 12);
    myHeadGear = new HeadGear("Worse Hat", 7);
    myHandGear1 = new HandGear("Better Gloves", 10);
    myHandGear2 = new HandGear("Worse Ring", 7);
  }

  @Test
  public void testGetName() {
    assertEquals("Batman", character1.getName());
  }
  
  @Test
  public void testGetAttackVal() {
    assertEquals(500, character1.getAttackVal());
  }
  
  @Test
  public void testGetDefenseVal() {
    assertEquals(400, character1.getDefenseVal());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidAttackVal() {
    new Character("Batman", -10, 10);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDefenseVal() {
    new Character("Batman", 10, -10);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDressName() {
    new Character("", 10, 10);
  }
  
  @Test
  public void testAddDress() {
    character1.addDress(myFootWear2);
    assertEquals(7, character1.getDressList().get(0).getAttackVal());
    assertEquals(12, character1.getDressList().get(0).getDefenseVal());
    assertEquals("Worse Sandal", character1.getDressList().get(0).getName());
    
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidDressAdded() {
    character1.addDress(myFootWear1);
    character1.addDress(myFootWear2);
    character1.addDress(myHandGear1);
    character1.addDress(myHandGear2);
    character1.addDress(myHeadGear);
    character1.addDress(myHandGear1);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidFootWearAdded() {
    character1.addDress(myFootWear1);
    character1.addDress(myFootWear2);
    character1.addDress(myFootWear1);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidHandGearAdded() {
    character1.addDress(myHandGear1);
    character1.addDress(myHandGear2);
    character1.addDress(myHandGear1);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidHeadGearAdded() {
    character1.addDress(myHeadGear);
    character1.addDress(myHeadGear);
  }
  
  @Test
  public void testGetTotalDresses() {
    character1.addDress(myFootWear2);
    character1.addDress(myFootWear1);
    assertEquals(2, character1.getTotalDresses());
  }
  
  @Test
  public void testSwapDress() {
    character1.addDress(myFootWear2);
    character1.swapDress(myFootWear1, "FootWear");
    assertEquals(20, character1.getDressList().get(0).getAttackVal());
  }

  @Test(expected = IllegalStateException.class)
  public void testIfInvalidStrengthAdded() {
    character2.addDress(myHandGear1);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidStrengthAdded2() {
    character2.addDress(myHeadGear);
  }
}
