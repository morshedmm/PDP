package mazes;

/**
 * Represents a bottomless pit in the game.
 * 
 */
public class BottomlessPit implements CaveObject {
  
  private String message;
  
  /**
   * Creates a bottomless pit object.
   */
  public BottomlessPit() {
    this.message = "You have fallen in bottomless cave";
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
    
    return true;
  }

  @Override
  public boolean equalsSuperBat() {
    
    return false;
  }

}
