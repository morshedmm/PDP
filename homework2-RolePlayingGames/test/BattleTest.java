
import static org.junit.Assert.assertEquals;

import battle.Battle;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for Battle class.
 * 
 */
public class BattleTest {

  private Battle battle;
  
  /**
   * Sets up a battle to test with characters and dresses.
   */
  @Before
  public void setUp() {
    battle = new Battle();
    battle.addCharacter("Batman", 500, 400);
    battle.addCharacter("Spiderman", 550, 440);
    battle.addHeadGear("Stronger Helmet", 5);
    battle.addHeadGear("Zombie Cap", 7);
    battle.addHandGear("Scary Gloves", 13);
    battle.addHandGear("Beautiful Ring", 23);
    battle.addHandGear("Larger Gloves", 16);
    battle.addHandGear("Pathetic Nail", 30);
    battle.addFootWear("Beautiful Sandals", 20, 10);
    battle.addFootWear("Ugly Boot", 10, 5);
    battle.addFootWear("Smaller Sock", 17, 8);
    battle.addFootWear("Larger Shoe", 8, 11);
  }
  
  @Test
  public void testGetNumOfDresses() {
    assertEquals(10, battle.getNumOfDresses());
  }
  
  @Test
  public void testGetNumOfCharacters() {
    assertEquals(2, battle.getNumOfCharacters());
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidDressAdded1() {
    battle.addFootWear("Larger Shoe", 8, 11);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidDressAdded2() {
    battle.addHeadGear("Stronger Helmet", 5);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidDressAdded3() {
    battle.addHandGear("Scary Gloves", 13);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfInvalidCharacterAdded() {
    battle.addCharacter("Batman", 500, 400);
  }
  
  @Test
  public void testBattleResult1() {
    
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman"); 
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    
    assertEquals("Batman Won!!", battle.printWinner());
  }
  
  @Test
  public void testBattleResult2() {
    
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    
    assertEquals("Spiderman Won!!", battle.printWinner());
  }
  
  @Test(expected = IllegalStateException.class)
  public void testIfEnoughCharacterAdded() {
    Battle battle1 = new Battle();
    battle1.addCharacter("Batman", 500, 400);
    battle1.printWinner();
  }
  
  @Test
  public void testBattleResult3() {
    Battle battle1 = new Battle();
    battle1.addCharacter("Batman", 500, 400);
    battle1.addCharacter("Spiderman", 550, 440);
    battle1.addHeadGear("Stronger Helmet", 5);
    battle1.addHeadGear("Zombie Cap", 5);
    battle1.addHandGear("Scary Gloves", 13);
    battle1.addHandGear("Beautiful Ring", 13);
    battle1.addHandGear("Larger Gloves", 13);
    battle1.addHandGear("Pathetic Nail", 13);
    battle1.addFootWear("Beautiful Sandals", 20, 20);
    battle1.addFootWear("Ugly Boot", 20, 20);
    battle1.addFootWear("Smaller Sock", 20, 20);
    battle1.addFootWear("Larger Shoe", 20, 20);
    
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman"); 
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    
    assertEquals("Tie!!", battle1.printWinner());
    
    
  }
  
  @Test
  public void testCharacterInfo() {
    Battle battle1 = new Battle();
    battle1.addCharacter("Batman", 500, 400);
    battle1.addFootWear("Beautiful Sandals", 20, 10);
    battle1.addFootWear("Ugly Boot", 10, 5);
    battle1.addHandGear("Scary Gloves", 13);
    
    battle1.assignDress("Batman");
    assertEquals("[Beautiful Sandals -- defense strength: 10, attack strength: 20]",
        battle1.characterInfo("Batman"));
  }
  
  @Test
  public void testIfMergableDressPickedFirst() {
    Battle battle1 = new Battle();
    battle1.addCharacter("Batman", 500, 400);
    battle1.addHandGear("Scary Gloves", 23);
    battle1.addHeadGear("Stronger Helmet", 5);
    battle1.addFootWear("Beautiful Sandals", 20, 10);
    
    battle1.assignDress("Batman");
    assertEquals("Scary Gloves", battle1.getCharacterList().get(0).getDressList()
        .get(0).getName());
    
  }
  
  @Test
  public void testIfHigherAttackValueDressPickedFirst() {
    Battle battle1 = new Battle();
    battle1.addCharacter("Batman", 500, 400);
    battle1.addHandGear("Scary Gloves", 23);
    battle1.addHeadGear("Stronger Helmet", 5);
    battle1.addFootWear("Beautiful Sandals", 30, 10);
    
    battle1.assignDress("Batman");
    assertEquals("Beautiful Sandals", battle1.getCharacterList().get(0).getDressList()
        .get(0).getName());
    
  }
  
  @Test
  public void testIfHigherDefensekValueDressPickedIfEqualAttackValues() {
    Battle battle1 = new Battle();
    battle1.addCharacter("Batman", 500, 400);
    battle1.addHandGear("Scary Gloves", 30);
    battle1.addHeadGear("Stronger Helmet", 5);
    battle1.addFootWear("Beautiful Sandals", 30, 10);
    
    battle1.assignDress("Batman");
    assertEquals("Beautiful Sandals", battle1.getCharacterList().get(0).getDressList()
        .get(0).getName());
    
  }

}
