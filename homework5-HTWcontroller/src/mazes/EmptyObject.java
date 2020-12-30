package mazes;

/**
 * Represent an empty object that tells nothing present in the location of the maze.
 * 
 */
public class EmptyObject implements CaveObject {
  
  private String message;
  
  /**
   * Creates an Empty object.
   */
  public EmptyObject() {
    this.message = "";
  }
  

  @Override
  public String getMessage() {
    
    return this.message;
  }

  @Override
  public boolean equalsWumpus() {
    
    return false;
  }

  @Override
  public boolean equalsBottomlessPit() {
    
    return false;
  }

  @Override
  public boolean equalsSuperBat() {
    
    return false;
  }

}
