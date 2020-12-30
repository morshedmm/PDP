

import static org.junit.Assert.assertEquals;

import mazes.Gold;
import mazes.NoPrize;
import mazes.Prize;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for Gold Class.
 */
public class GoldTest {
  
  private Prize prize;
  
  @Before
  public void setUp() {
    prize = new Gold(10);
  }
  
  @Test
  public void testGoldValue() {
    assertEquals(10, prize.getPrizeValue());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidGoldValue() {
    new Gold(-10);
  }
  
  @Test
  public void testEqualsGold() {
    assertEquals(true, prize.equalsGold());
  }
  
  @Test
  public void testEqualsGold2() {
    Prize prize2 = new NoPrize();
    assertEquals(false, prize2.equalsGold());
  }
  
  @Test
  public void testToString() {
    assertEquals("PrizeValue: 10", prize.toString());
  }

}
