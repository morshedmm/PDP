

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import codes.CharacterElement;

/**
 * A JUnit test class for CharacterElement class.
 * 
 */
public class CharacterElementTest {
  
  private CharacterElement element1;
  private CharacterElement element2;
  
  @Before
  public void setUp() {
    element1 = new CharacterElement("s", 8);
    element2 = new CharacterElement("h", 4);
  }

  @Test
  public void testConstructor() {
    assertEquals("s+8", element1.toString());
  }
  
  @Test
  public void testCompareTo() {
    assertEquals(-1, element2.compareTo(element1));
  }
  
  @Test
  public void testCompareTo2() {
    CharacterElement element3 = new CharacterElement("BORT", 4);
    CharacterElement element4 = new CharacterElement("H", 4);
    assertEquals(-1, element3.compareTo(element4));
  }
  
  @Test
  public void testCompareTo3() {
    CharacterElement element3 = new CharacterElement("BORT", 4);
    CharacterElement element4 = new CharacterElement("B", 4);
    assertEquals(1, element3.compareTo(element4));
  }
  
  @Test
  public void testCompareTo4() {
    assertEquals(1, element1.compareTo(element2));
  }
  
  @Test
  public void testCompareTo5() {
    CharacterElement element3 = new CharacterElement("BORT", 4);
    CharacterElement element4 = new CharacterElement("H", 4);
    assertEquals(1, element4.compareTo(element3));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidArgument() {
    new CharacterElement("a", 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidArgument2() {
    new CharacterElement("", 1);
  }

}
