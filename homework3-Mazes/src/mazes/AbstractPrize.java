package mazes;

/**
 * Implements generic Prize Interface.
 *
 */
public class AbstractPrize implements Prize {
  
  protected int prizeValue = 0;
  protected double prizeFactor = 1.0;
  
  /**
   * Builds a Prize with all fields.
   * @param value gold value
   * @param factor thief factor
   * @throws IllegalArgumentException if values are not legal
   */
  public AbstractPrize(int value, double factor)
      throws IllegalArgumentException {
    if (value < 0 || factor > 1) {
      throw new IllegalArgumentException("value must be positive, factor cannot be more than 1!!");
    }
    this.prizeValue = value;
    this.prizeFactor = factor;
  }
  
  /**
   * Builds a Prize with gold value.
   * @param value value of the gold
   * @throws IllegalArgumentException if gold value is not legal
   */
  public AbstractPrize(int value)
      throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException("value must be positive!!");
    }
    this.prizeValue = value;
  }
  
  /**
   * Builds a Prize with thief factor.
   * @param factor by what thief will leave gold for player after stealing
   * @throws IllegalArgumentException if factor is not legal
   */
  public AbstractPrize(double factor)
      throws IllegalArgumentException {
    if (factor > 1) {
      throw new IllegalArgumentException("factor cannot be more than 1!!");
    }
    this.prizeFactor = factor;
  }
  
  /**
   * Builds prize for room with no prize.
   */
  public AbstractPrize() {
    this.prizeValue = 0;
    this.prizeFactor = 1.0;
  }
  
  @Override
  public int getPrizeValue() {
    return this.prizeValue;
  }
  
  @Override
  public double getFactor() {
    return this.prizeFactor;
  }
  
  @Override
  public String toString() {
    return String.format("PrizeValue: %d", this.prizeValue);
  }

  @Override
  public boolean equalsGold() {
    return false;
  }

  @Override
  public boolean equalsGoldThief() {
    return false;
  }

}
