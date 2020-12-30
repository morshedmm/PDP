

import static org.junit.Assert.assertEquals;

import mazes.GoldThief;
import mazes.NoPrize;
import mazes.Prize;
import org.junit.Before;
import org.junit.Test;



/**
 * A JUnit test class for GoldThief class.
 * 
 */
public class GoldThiefTest {

  private Prize prize;
  
  @Before
  public void setUp() {
    prize = new GoldThief(10, 0.1);
  }
  
  @Test
  public void testGoldValue() {
    assertEquals(10, prize.getPrizeValue());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidGoldValue() {
    new GoldThief(-10, 0.1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThiefRatio() {
    new GoldThief(10, 2.0);
  }
  
  @Test
  public void testEqualsGoldThief() {
    assertEquals(true, prize.equalsGoldThief());
  }
  
  @Test
  public void testEqualsGold2() {
    Prize prize2 = new NoPrize();
    assertEquals(false, prize2.equalsGoldThief());
  }

}
