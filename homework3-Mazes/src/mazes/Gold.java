package mazes;

/**
 * Represents a prize with only gold for a room.
 * 
 */
public class Gold extends AbstractPrize {
  
  /**
   * Creates Gold object.
   * @param value value of the gold
   * @throws IllegalArgumentException if gold value is not legal
   */
  public Gold(int value) 
      throws IllegalArgumentException {
    super(value);
  }
  
  @Override
  public boolean equalsGold() {
    return true;
  }

}
