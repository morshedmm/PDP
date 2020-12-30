package mazes;

/**
 * Represents a thief.
 * 
 */
public class Thief extends AbstractPrize {
  
  /**
   * Creates a thief.
   * @param factor by which gold will remain after stealing
   * @throws IllegalArgumentException if value is not legal
   */
  public Thief(double factor) 
      throws IllegalArgumentException {
    super(factor);
  }

}
