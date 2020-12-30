

import static org.junit.Assert.assertEquals;

import mazes.Prize;
import mazes.Thief;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for Thief class.
 * 
 */
public class ThiefTest {

  private Prize prize;
  
  @Before
  public void setUp() {
    prize = new Thief(0.1);
  }
  
  @Test
  public void testThiefValue() {
    assertEquals(0.1, prize.getPrizeValue(), 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidThieftRatio() {
    new Thief(2.0);
  }

}
