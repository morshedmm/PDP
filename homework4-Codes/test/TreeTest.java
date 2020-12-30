

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import codes.Tree;

/**
 * A JUnit test class for Tree class.
 * 
 */
public class TreeTest {
  
  private Tree myTree;
  private Map<String, String> myCode;
  
  @Before
  public void setUp() {
    myCode = new HashMap<String, String>();
    myCode.put("a", "100");
    myCode.put("b", "00");
    myCode.put("c", "01");
    myCode.put("d", "11");
    myCode.put("e", "101");
  }

  @Test
  public void testDecode1() {
    myTree = new Tree(myCode);
    assertEquals("ace", myTree.decode("10001101"));
  }
  
  @Test
  public void testDecode2() {
    myTree = new Tree(myCode);
    assertEquals("abcde", myTree.decode("100000111101"));
  }
  
  @Test
  public void testDecode3() {
    Map<String, String> myCode1 = new HashMap<String, String>();
    myCode1.put("S", "10");
    myCode1.put("H", "010");
    myCode1.put("E", "111");
    myCode1.put(" ", "110");
    myCode1.put("L", "011");
    myCode1.put("A", "0001");
    myCode1.put("B", "00100");
    myCode1.put("Y", "0000");
    myCode1.put("T", "00111");
    myCode1.put("O", "00101");
    myCode1.put("R", "00110");
    myTree = new Tree(myCode1);
    assertEquals("SHE SELLS SHELLS", myTree.decode("1001011111010111011011101101001011101101110"));
  }
  
  @Test
  public void testDecode4() {
    Map<String, String> myCode1 = new HashMap<String, String>();
    myCode1.put("a", "A");
    myCode1.put("c", "BC");
    myCode1.put(".", "8");
    myCode1.put("?", "6");
    
    myTree = new Tree(myCode1);
    assertEquals("ca.?", myTree.decode("BCA86"));
  }
  
  @Test
  public void testDecode5() {
    Map<String, String> myCode1 = new HashMap<String, String>();
    myCode1.put("S", "10");
    myCode1.put("H", "010");
    myCode1.put("E", "111");
    myCode1.put(" ", "110");
    myCode1.put("L", "011");
    myCode1.put("A", "0001");
    myCode1.put("B", "00100");
    myCode1.put("Y", "0000");
    myCode1.put("T", "00111");
    myCode1.put("O", "00101");
    myCode1.put("R", "00110");
    myTree = new Tree(myCode1);
    assertEquals("SHE SELLS SEA SHELLS BY THE SEA SHORE", myTree.decode("1001011111010111011011"
        + "10110101110001110100101110110111011000100000011000111010111110"
        + "101110001110100100010100110111"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidArgument() {
    myTree = new Tree(new HashMap<String, String>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDecodeAttempt() {
    
    Map<String, String> myCode1 = new HashMap<String, String>();
    myCode1.put("a", "A");
    myCode1.put("c", "BC");
    myCode1.put(".", "8");
    myCode1.put("?", "6");
    
    myTree = new Tree(myCode1);
    myTree.decode("");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidTreeKey() {
    
    Map<String, String> myCode1 = new HashMap<String, String>();
    myCode1.put("a", "A");
    myCode1.put("c", "BC");
    myCode1.put(".", "8");
    myCode1.put("?", "");
    
    myTree = new Tree(myCode1);
    myTree.decode("BCA8");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidSequence() {
    Map<String, String> myCode2 = new HashMap<String, String>();
    myCode2.put("a", "100");
    myCode2.put("b", "00");
    myCode2.put("c", "01");
    myTree = new Tree(myCode2);
    assertEquals("a", myTree.decode("101"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidSequence2() {
    Map<String, String> myCode2 = new HashMap<String, String>();
    myCode2.put("a", "100");
    myCode2.put("b", "00");
    myCode2.put("c", "01");
    myTree = new Tree(myCode2);
    assertEquals("a", myTree.decode("10"));
  }

}
