package mazes;

/**
 * Represents a prize with both gold and thief for a room.
 * 
 */
public class GoldThief extends AbstractPrize {
  
  /**
   * Builds a prize containing both gold and thief.
   * @param value value of the gold
   * @param factor at what factor gold will remain after stealing
   * @throws IllegalArgumentException if values are not legal
   */
  public GoldThief(int value, double factor) 
      throws IllegalArgumentException {
    super(value, factor);
  }
  
  @Override
  public boolean equalsGoldThief() {
    return true;
  }

}
