package mazes;

/**
 * Represents a generic object in the cave.
 * 
 */
public interface CaveObject {
  
  /**
   * Gives the message to represent characteristics of an object.
   * @return a string with the characteristic
   */
  public String getMessage();
  
  /**
   * Checks if this object equals to Wumpus.
   * @return a boolean to give the result
   */
  public boolean equalsWumpus();
  
  /**
   * Checks if this object equals to Pit.
   * @return a boolean to give the result
   */
  public boolean equalsBottomlessPit();
  
  /**
   * Checks if this object equals to Bat.
   * @return a boolean to give the result
   */
  public boolean equalsSuperBat();

}
