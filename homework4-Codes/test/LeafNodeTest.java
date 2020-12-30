

import org.junit.Test;

import codes.LeafNode;

/**
 * A JUnit test class for LeafNode.
 * 
 */
public class LeafNodeTest {
  

  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidArgument() {
    new LeafNode("");
  }

}
