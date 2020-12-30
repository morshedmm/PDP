

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import codes.HuffmanEncoding;

/**
 * A JUnit test class for HuffmanEncoding class.
 * 
 */
public class HuffmanEncodingTest {

  @Test
  public void testConstructor() {
    HuffmanEncoding myEncoder = new HuffmanEncoding("she sells sea shells by the sea shore", "01");
    assertEquals(" :110\na:0001\nb:00100\nr:00110\ns:10\nt:00111\ne:111\nh:010\ny:0000\nl"
        + ":011\no:00101\n", myEncoder.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidArgument() {
    new HuffmanEncoding("", "01");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidArgument2() {
    new HuffmanEncoding("she sells sea shells by the sea shore", "");
  }
  
  @Test
  public void testConstructor2() {
    HuffmanEncoding myEncoder = new HuffmanEncoding("this is a windy day! we should be "
        + "careful; what are we doing may "
        + "cause some issues to our health", "234CDEF");
    assertEquals(" :3\na:CD\n!:CF2\nb:CF4\nc:CFE\nd:4D\ne:2\nf:"
        + "CFC\ng:CFD\nh:C2\ni:C3\nl:43\nm:CFF\nn:42\no:"
        + "C4\nr:44\ns:CE\nt:4E\nu:CC\nw:4F\ny:4C\n;:CF3\n", myEncoder.toString());
  }
  
  @Test
  public void testConstructorWithLineBreak() {
    HuffmanEncoding myEncoder = new HuffmanEncoding("she\n", "01");
    assertEquals("s:11\ne:01\nh:10\n\n:00\n", myEncoder.toString());
  }
  
  

}
