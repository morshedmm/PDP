

import static org.junit.Assert.assertEquals;

import mazes.NoPrize;
import mazes.Prize;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for NoPrize class.
 * 
 */
public class NoPrizeTest {
  
  private Prize prize;
  
  @Before
  public void setUp() {
    prize = new NoPrize();
  }

  @Test
  public void testPrizeValue() {
    assertEquals(0, prize.getPrizeValue());
  }
  
  @Test
  public void testThiefRatio() {
    assertEquals(1.0, prize.getFactor(), 1);
  }

}
