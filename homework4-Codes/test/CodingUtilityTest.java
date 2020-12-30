

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import codes.CodingUtility;

/**
 * A JUnit test class for CodingUtility class.
 * 
 */
public class CodingUtilityTest {
  
  private CodingUtility myCoding = new CodingUtility();

  @Test
  public void testDoEncode() {
    assertEquals("1000001", myCoding.doEncode("abc", "a 100 b 00 c 01"));
  }
  
  @Test
  public void testDoDecode() {
    assertEquals("ca.?", myCoding.doDecode("BCA86", "a A c BC . 8 ? 6"));
  }
  
  @Test
  public void testHuffmanEncoding() {
    assertEquals(" :110\na:0001\nb:00100\nr:00110\ns:10\nt:00111\ne:111\nh:010\ny"
        + ":0000\nl:011\no:00101\n", myCoding.doHuffmanEncoding("she sells "
            + "sea shells by the sea shore", "01"));
  }
  
  @Test
  public void testDoEncode2() {
    assertEquals("41", myCoding.doEncodeToHex("abc", "a 100 b 00 c 01"));
  }
  
  @Test
  public void testDoEncode3() {
    assertEquals("61D", myCoding.doEncodeToHex("cabde", "a 100 b 00 c 01 d 11 e 101"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidBinaryKeys() {
    myCoding.doEncodeToHex("cabde", "a 100 b 00 c 01 d 11 e 1a1");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidEncodeArgument() {
    myCoding.doEncode("", "a 100 b 00 c 01");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidEncodeArgument2() {
    myCoding.doEncode("abc", "");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDecodeArgument() {
    myCoding.doDecode("", "a A c BC . 8 ? 6");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDecodeArgument2() {
    myCoding.doDecode("BCA86", "");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidHuffmanEncodingArgument() {
    myCoding.doHuffmanEncoding("she sells "
        + "sea shells by the sea shore", "");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidHuffmanEncodingArgument2() {
    myCoding.doHuffmanEncoding("", "01");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfNotPrefixCode() {
    myCoding.doEncode("abc", "a 10 b 101 c 11");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfNotPrefixCode2() {
    myCoding.doDecode("BCA86", "a A c AC . 8 ? 6");
  }

}
