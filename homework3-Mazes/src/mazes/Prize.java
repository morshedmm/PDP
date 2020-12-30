package mazes;

/**
 * Represents the interface for Prize.
 */
public interface Prize {
  
  /**
   * Gives the prize value.
   * @return value of gold in integer
   */
  public int getPrizeValue(); 
  
  /**
   * Gives the factor at which gold will remain after stealing.
   * @return factor in double
   */
  public double getFactor(); 
  
  /**
   * Tells if this Prize is of type Gold.
   * @return boolean to give equality
   */
  public boolean equalsGold();
  
  /**
   * Tells if this prize is both gold and thief.
   * @return boolean to give equality
   */
  public boolean equalsGoldThief();


}
